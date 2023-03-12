# This is a sample Python script.
import sys
import pandas
import itertools, functools

emotions = ['Angry', 'Happy', 'Neutral', 'Sad', 'Surprise']
dropParameters = ['QuestionKey', 'Gender', 'Diagnosis', 'PHQ8Score']
action = 'UniqueQuestion'

def debug(df, hnum = 10):
    tempDF = df.head(hnum)
    print(tempDF)
    sh = df.shape
    print('The shape is ' + str(sh))

def preFiltering(df,ds,aps):
    df = df[df.Dataset.isin(ds)]
    for param in aps:
        dropParameters.remove(param)
    df = df.drop(columns=dropParameters)
    return df

def extractPreviousEVs(masterlog, index):
    this = masterlog[masterlog.index == index]
    user = this['Case'].values[0]
    step = this['Step'].values[0]
    row = masterlog[(masterlog['Case'] == user) & (masterlog['Step'] == (step-1))]
    retval = extractSuccEVs(masterlog, row.index.values[0])
    return retval

def extractSuccEVs(masterlog, index):
    retval = list()
    this = masterlog[masterlog.index == index]
    step = this['Step'].values[0]
    for e in emotions:
        value = this[e].values[0]
        retval.append(value)
    return retval

def delta(masterlog,index):
    succEVs = extractSuccEVs(masterlog,index)
    previousEVs = extractPreviousEVs(masterlog,index)
    delta = list(map(lambda x,y: x - y,succEVs,previousEVs))
    return delta

def commandLineReading(arguments):
    log = open(arguments[0])
    params = arguments[1].split(';')
    datasets = arguments[2].split((';'))
    return log, params, datasets


if __name__ == '__main__':
    rMatrix = dict()
    # Reading command line parameters
    ilog, actualParameters, applicableDatasets = commandLineReading(sys.argv[1:])
    # Filtering
    ilogDF = pandas.read_csv(ilog, delimiter=';', header = 0)
    ilogDF = preFiltering(ilogDF,applicableDatasets,actualParameters)
    # Grouping
    parameterValues = list()
    parameterValuesProduct = list()
    for p in actualParameters:
        parameterValues.append(list(ilogDF[p].unique()))
    for element in itertools.product(*parameterValues):
        parameterValuesProduct.append(element)
    parameterProductColumnNames = tuple(actualParameters)
    # Starting from actions
    actionValues = list(ilogDF[action].unique())
    for av in actionValues:
        avFilteredDF = ilogDF[(ilogDF[action] == av) & (ilogDF['Step'] > 1)]
        if avFilteredDF.empty == False:
            rMatrix[av] = dict()
            for pv in parameterValuesProduct:
                paramFilteredDF = pandas.DataFrame(avFilteredDF)
                for i in range(0, len(parameterProductColumnNames)):
                    column = parameterProductColumnNames[i]
                    value = pv[i]
                    paramFilteredDF = paramFilteredDF[paramFilteredDF[column] == value]
                if (paramFilteredDF.empty == False):
                    deltas = list()
                    indices = paramFilteredDF.index.values.tolist()
                    for i in indices:
                        deltas.append(delta(ilogDF,i))
                    deltas = list(functools.reduce(lambda x,y: x + y,deltas))
                    deltas = list(map(lambda x: x/len(indices),deltas))
                    rMatrix[av][pv] = dict(zip(emotions, deltas))
    ilog.close()
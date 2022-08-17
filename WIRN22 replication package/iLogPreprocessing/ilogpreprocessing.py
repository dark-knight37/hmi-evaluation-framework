# This is a sample Python script.
import sys
import csv

lookup = dict()
questions = dict()

def makequestions(fname):
    retval = dict()
    fhandle = open(fname)
    reader = csv.reader(fhandle, delimiter=',')
    for row in list(reader)[1:]:
        retval[row[0]] = row[1].strip()
    fhandle.close()
    return retval

def getUniqueQuestion(questionkey):
    desc = questions[questionkey]
    retkey = lookup.get(desc, None)
    if retkey == None:
        retkey = 'UQ' + str(len(lookup.keys()) + 1)
        lookup[desc] = retkey
    return retkey

if __name__ == '__main__':
    main_file = open(sys.argv[1])
    questions = makequestions(sys.argv[2])
    out_file = open(sys.argv[3],'w')
    lookup_file = open(sys.argv[4],'w')
    out_file.write('Dataset;Case;Step;QuestionKey;Gender;Diagnosis;PHQ8Score;Angry;Happy;Neutral;Sad;Surprise;UniqueQuestion\n')
    main_reader = csv.reader(main_file, delimiter=';')
    for row in list(main_reader)[1:]:
        temp = list(row)
        retkey = getUniqueQuestion(temp[3])
        temp_str = ';'.join(temp) + ';' + retkey + '\n'
        out_file.write(temp_str)
    out_file.close()
    for k in lookup.keys():
        lookup_file.write(lookup[k] + '\t' + k + '\n')
    lookup_file.close()
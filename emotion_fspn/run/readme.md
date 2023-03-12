# Paper replication package
This folder is devoted to contain the support material useful for replicating the research contained in the paper *Inferring Emotional Models from Human-Machine Speech Interactions*.

At this date, this paper has not been published: it is under revision. A preliminary discussion of the topics contained in the paper has been held during the 30th Italian Workshop on Neural Networks (WIRN) https://www.siren-neural-net.it/wirn-2022/

Please, refer to the paper to fully understand the meaning of the items reported in this repository. In case the paper is not published yet, contact stefano.marrone@unicampania.it for information.

## Structure
The folder contains the following material:
* *src*: folder containing the Java source code  
* *interaction.csv*: the first part of the interaction log, containing the most of the information; 
* *questions.csv*: the second part of the interaction log, containing the textual transcriptions of the questions;
* *ilogpreprocessing.py*: Python script that takes as inputs the `interaction_file` (*interactions.csv*) and the `question_file` (*questions.csv*) to make some preliminary processing. The output of the script are the `processed_file` (*processed.csv*) and the `question_dictionary` (*question_dict.txt*) that contains the dump of the dictionary between question keys and transcriptions. This script is used by specifying such files on the command line: `ilogpreprocessing.py interaction_file question_file processed_file question_dictionary`;
* *processed.csv*: first output of the *ilogprocessing.py*; 
* *questions_dictionary.txt*: second  output of the *ilogprocessing.py*; 
* *usr_building.py*: Python script that takes as inputs the `processed_file` (*processed.csv*) and the specifications of two lists, `params` (a semi-colon separated list of the parameters that are used as user features), and `datasets` (a semi-colon separated list of the dataset tags that are considered in the building process);
* *agentmodel.pnml*: PNML model obtained by running the ProM tool on the *processed.csv* file.

## Usage
To replicate the analysis contained in 

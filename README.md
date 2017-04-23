# Memory
A NLP based bot that can remember things.

## Project Source can be downloaded from https://github.com/Vabs123/Memory

## Contributor
Vaibhav Grover

## How to run Memory Bot

1. Clone the directory to your local machine.
2. Please replace the api key with yours in ```NLPImplementation.java``` in ```nlp``` folder.
3. Go to ```/Memory/main/src/run```.
4. Open ```terminal``` or ```cmd``` in the same directory.
5. Run this command ```java -cp .:/...path to Memory folder..../Memory/main/classes run.AnswerQuery```.

## Working of Memory Bot
User can give inputs as text which he wants the bot to remember. In background the bot is using NLP api [TextRazor](https://www.textrazor.com/). For complete information please read the api [java documentation](https://www.textrazor.com/docs/java). After processing, text is saved as ```memory object``` which can be retrieved when user asks question about that.

## Sample Of Bot Working
![screenshot from 2017-04-23 19-52-01](https://cloud.githubusercontent.com/assets/9266813/25314537/bf74ce38-2863-11e7-8600-7f70f0d2bdde.png)

## Future Work
Only thing left was to categorize each word in one of the four categories as mentioned below:

* people/person - will answer for who, whose, whom.
* place - will answer for where, at what place.
* time - when, at what time.
* other - what and any other types of questions.




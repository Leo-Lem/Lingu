# Lingu

Learning a new language? Lingu is your best friend from now on.

Lingu is an interactive-chat vocabulary trainer. The CLI version is completely conversation-based. Currently, the main features are:

## Registering as a new user

The first time you start the app (or when the JSON file storage is deleted), Lingu will prompt you for your name, the language you want to learn, and the language you want to translate from. This information is immediately persisted, even if you leave the app while registering, you will continue at the same point you left off.

## Changing your settings & information

After you have set up your account, Lingu asks you what to do next (think of it as the main menu). Beside the start learning option, there are several options to change your settings:

- Change the language you are learning (Available: English, German, Spanish, French).
- Change the language you are asked to translate from (Available: English, German, Spanish, French).
- Change the interface locale (Available: English, German, Spanish).
- Change your name.

All these are fully functional and Lingu will interactively update your information.

## Learning some vocabulary with a spaced repetition system

Now that you have adjusted the settings completely to your liking, it's time to get to the actual learning. When starting a learning session for the first time, some words will be generated and automatically translated to your chosen languages. Lingu will then ask you how to translate a given word and, after you give your answer, will provide feedback. Additionally, the spaced repetition algorithm calculates the next time, this word will be used (when you get a word right, the interval will be increased, when you get it wrong it will be reset).

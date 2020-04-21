My chat bot for telegram. To START: What you need // Linux (Ubuntu 16.04) , Java 8 (not less) , Intellig Idea, Git (2.7.4)

1. Open the folder where you want to place the project (for example, /home/user_name/IdeaProjects).

2. Open the command line (Bash shell) in this folder and enter command: git clone https://github.com/Ivanmc-007/ChatBot

3. Open the folder with project in Intellij Idea. Select auto-import for maven dependencies.

4. In the maven tab (top right corner) do MyChatBotTelegram -> Lifecycle -> clean -> Run Maven Build. (can skip)

5. You should go to Application.class and run public static void main(...) method.

P.S.: You can find botToken and botName in the file 'telegram.properties' in the 'src/main/resources' folder of project.

https://travis-ci.org/NJDaeger/EssentialCommands.svg?branch=master
# EssentialCommands
A remake of Bukkit's Essentials plugin. This will also include built in plugins like groupmanager (remade), editors, and probably a few more things once I get this fully sorted out.

Progress:
- NJPerms: 0%
- LoginClearance: 0%
- ServerProtect: 0%
- BannerManager: 0% (redoing this)
- Essentials: 40%
- Entire plugin: 10%

Possible/planned features:
- Revamped version of LoginClearance
- Server codes plugin
- External Mail receiving feature (this might be cancelled)
- Revamped version of ServerProtect
- Banner Creator
- Placed sign editor.
- Custom MOTD per world (option)
- Probably a few other things.

How to build:

- You need to have Maven installed and in your path in order for the plugin to compile.

- 1. Download the repository ZIP.
- 2. Take the contents of the zip file and put it in a new file.
- 3. Open a Git Bash window and run the command "mvn clean install"
- 4. After it's finished building, the compiled plugin will be in the target folder.
- 5. Drag jar file into your server plugins folder and restart the server.

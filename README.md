javac -d out -sourcepath src src/Sokoban.java

jar --create --file Sokoban.jar --main-class Sokoban.src.Sokoban -C out .

jpackage --name Sokoban --input . --main-jar Sokoban.jar --main-class Main --type exe
jpackage --name Sokoban --input . --main-jar Sokoban.jar --main-class Sokoban.src.Sokoban --type exe --resource-dir .
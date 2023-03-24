# bunnyHashSet
hashset project for COSC 310

JPHashSet - implements the HashSet class' methods to create a working hashset to be implemented by the bunny hash set project.

Given a list of bunny names in a text file, performs various operations on the text file, including adding, removing, searching, and mass deletion. 
Makes decisions based on user inputs.

JMPratchios0HashSet - 
This Program is built to help the BOBCAT initiative and to practice HashSets for COSC-310 
 * This program reads from a text file and performs various operations, including adding, removing, searching, mass extinction, etc.
 * Additionally, this program makes decisions based on user inputs, which are constantly checked as time goes on.

Below are conditionals for the user input.
contains some version of STOP or END -> ends the program.
contains ADD -> asks the user to add a new name to database. Returns true if successful, false if name exists.
contains DEL -> asks the user for a name to delete, and then removes that bunny from existence. returns false if bunny is not in database.
contains SEARCH or NAME -> searches for the given name. If found in database, returns true. returns false otherwise.
Contains STATS -> returns generic stats for the table.

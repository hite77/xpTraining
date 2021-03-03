Alter table GAME_RESULTS drop column player1Name;
Alter table GAME_RESULTS drop column player2Name;
Alter table GAME_RESULTS alter column player1Id int;
Alter table GAME_RESULTS alter column player2Id int;
Alter table GAME_RESULTS add column player1Throw varchar(20);
Alter table GAME_RESULTS add column player2Throw varchar(20);
Alter table GAME_RESULTS add column game_Time varchar(50);

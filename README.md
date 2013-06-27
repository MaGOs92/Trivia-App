TriviaCSV version 1.0 Input / Output des fichier CSV
======

Logiciel permettant de générer des reportings sur les données contenues dans un fichier CSV


Fonctionnement du programme : 

-	L’utilisateur configure l’application : choix de la connexion à la base de donné, choix du client (s’il existe déjà) ou création d’un nouveau client, choix des conditions spécifiques, …
-	L’utilisateur sélectionne le fichier qu’il veut auditer.
-	Le fichier CSV passé en paramètre devra respecter certaines conditions :
	o	Le séparateur doit être un « ; »
	o	La taille maximum du fichier est 100 Mo.
-	L’utilisateur indique le « benchmark target » attendu pour chaque classe.
-	L’application affecte à chaque champ une classe
-	L’application édite un fichier PDF sur lequel seront représenté des graphiques.

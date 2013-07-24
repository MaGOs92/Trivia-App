DataAudit Trivia
===============================

Programme réalisé dans le cadre d'un stage chez Trivia-marketing

Présentation :

DataAudit Trivia permet d'obtenir un rapport sur la qualité des données contenues dans un fichier .csv (uniquement)
Le programme détecte le nombre de valeurs vides et le nombre de valeurs incorrectes suivant un mapping que doit effectuer l'utilisateur.
L'utilisateur peut séléctionner certaines colonnes de son fichier et obtenir un rapport au format PDF sur les colonnes séléctionnées.

Installation :

Programmes requis pour utiliser DataAudit Trivia : 

	- Java RE
	
	- Serveur MySQL

Manipulations préalables :
	
	- Créer un compte admin avec un mot de passe par exemple root root sur le serveur MySQL.
	
	- Créer une base de donnée spécifique pour le programme.

Utilisation :

Démarrer le programme et entrer les informations de login au serveur MySQL dans le formulaire.

Exemple :

	Server : localhost (valeur par défaut)
	Port : 3306 (valeur par défaut)
	Database : Le nom de la base de donnée crée précédement
	User : root ( Utilisateur par défaut ou un autre utilisateur ayant les droits admin)
	Mdp : root (Par défaut l'utilisateur root n'a pas de mot de passe, il est préférable de lui en donner un)

- Appuyer sur le bouton tester la connection. Si vous obtenez le message "Connection failed. Please check your login information", vérifiez vos informations :) et vérifiez aussi que votre serveur MySQL est bien démarré (Ctrl + Alt + Supr = > Gestionnaire des tâches => Services => Vérifier que MySQl56 a le statut en cours.)

- Appuyer sur le bouton Next quand vous obtenez le message "Connected to MySQL Server".

- Séléctionner un fichier CSV avec le bouton "Browse"

ATTENTION !!! Vérifier que le fichier CSV est bien encodé au format UTF 8. Si ce n'est pas le cas, les accents ne s'afficheront pas et les chaines de caractères seront coupées à l'index des accents.

	Pour faire cette vérification ouvrir le fichier avec NotePad ++
	Dans la rubrique encodage vérifier que la séléction est UTF-8
	Si ce n'est pas le cas séléctionner convertir en UTF-8
	Enregistrer sous : monFichier-utf8.csv
	
- Appuyer sur le bouton "Start DataAudit" devient cliquable, après la séléction d'un fichier.

- Après le chargement du fichier dans la base de donnée (cette opération peut être longue, cela dépend de la taille du fichier audité), vous arrivez à l'écran de mapping.

- La liste à gauche de l'écran vous permet de séléctionner la colonne que vous voulez mapper

- La liste à droite de l'écran permet de sélectionner le mapping désiré

- Pour ajouter une colonne dens le rapport, cochez la checkBox au mileur de l'écran.

- Une fois le mapping effectué, cliuer sur "Edit repporting", un fichier PDF est généré dans le même dossier que votre fichier CSV.


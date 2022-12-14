# ECF4

## Elements nécessaires pour lancer l'application :
- JAVA 11+
- [Docker](https://www.docker.com/)
- Docker-compose
- Un IDE de type [Intellij](https://www.jetbrains.com/fr-fr/idea/) ou Eclipse


## Construction de l'application :
Aller dans le fichier Maven et charger le fichier afin d'importer les dépendences.
fichier : 
*pom.xml

## Lancement et connexion à la base de données
Pour utiliser cet API il est nécessaire de lancer un MangoDB.
On peut le faire dans un conteneur sur docker. Pour cela il est nécessaire de lancer ce conteneur grâce à la commande :
 
```docker-compose up -d ```

dans le terminal.

## Lancement de l'api
Dans le fichier >> ```fr.mebg.spring.scfquatre.EcfApplication```

Il faut appuyer sur la flèche verte afin de lancer l'application.
Cette application tourne sur le [localhost:8080](http://localhost:8080/).

[Postman](https://www.postman.com/) peut être utilisé afin de tester l'application sans front.

### Front de l'application :
Ce back doit/va normalement être connecté au front présent sur [gitHub](https://github.com/MathildeBettembourg/EFC3).
Le Front est en REACT-JS.

### Objectif du Back et de l'application.
Dans le cadre d'un contrôle continue à M2i il nous est demandé de construire une application de location de voitures.

Cette application permet de gérer trois entités via un CRUD classique : 
- Voitures 
- Locataires
- Locations

Connectée au back elle devra permettre une gestion de ces trois entités grâce à l'API REST connectée à une base de donnée Mango.

L'utilisation de Logger permet de tracker les évènements du back. 

Ainsi grâce à d'autres outils comme Kibana on peut avoir un panorama des évenements qui ont eu lieu sur l'API permettant de régler des failles ou tracker des problèmes.

L'ajout des CrossOrigin permet d'éviter le bloquage par les CrossPolicy.


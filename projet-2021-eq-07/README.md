## Description

API Rest en java développée en équipe de 8 dans le cadre du cours de Qualité et métriques du logiciel, GLO-4002 à l'université Laval en 2021. L'API sert de backend pour un jeu de gestion de parc zoologique de dinosaures où on peut "commander" de nouveaux dinosaures, accoupler des dinosaures et organiser des combats. Le fonctionnement de l'API était dictée dans l'énoncée mais aucun code n'était fourni.

## Intégration Docker

Pour rouler le code sur les mêmes images docker que les professeurs.

Pour ce faire:

```bash
docker build -t application-glo4002 .
docker run -p 8080:8080 -p 8181:8181 application-glo4002
```

## Démarrer le projet

Vous pouvez démarrer soit un des deux serveurs (GamerServer ou VetServer) individuellement ou simultanément.

Pour rouler le serveur via maven (**c'est ce qui sera utilisé pour corriger le projet**) :

```bash
mvn clean install
mvn exec:java -pl application
```

Pour formatter le code :

```bash
mvn spotless:apply
```

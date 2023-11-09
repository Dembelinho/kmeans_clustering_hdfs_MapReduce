# kmeans_clustering_hdfs_MapReduce

## K-Means

**K-Means** est un algorithme de clustering qui partitionne un ensemble de points de données en k clusters (Figure ci-dessus). 

L'algorithme de clustering k-means est couramment utilisé sur de grands ensembles de données et, en raison des caractéristiques de l'algorithme, est un bon candidat pour la parallélisation. 

Le but de cet exercice est d’implémenter k-means en utilisant Hadoop MapReduce.

![image](https://github.com/Dembelinho/kmeans_clustering_hdfs_MapReduce/assets/110602716/ebdc3821-afb5-4c3c-8871-ca4e7b2325b4)


## HDFS

**HDFS** divise les fichiers volumineux en blocs plus petits, dont la taille est généralement de 128 ou 256 Mo.
Ces blocs sont répartis sur plusieurs machines (nœuds) dans un cluster Hadoop.
Cette répartition permet à HDFS de stocker et de traiter efficacement les fichiers volumineux.

## Structure du projet

```
├───.idea
└───src
    ├───main
    │   ├───java
    │   │   └───org
    │   │       └───example
    │   └───resources
    └───test
        └───java

```
## Implémenter k-means en utilisant Hadoop & MapReduce

les étapes à suivre :
### a) 
> sélectionner k centroïdes aléatoires;
### b) 
> lire les points (à partir d'un fichier txt) et puis les affectés aux centroïdes les plus proches, en utilisant une mesure de distance euclédienne;(chaque point est caractérisé par x et y)
La méthode map reçoit en paramètre comme valeur la ligne contenant les valeurs de x et de y du point, puis trouve le centroïde le plus proche au point en calculant la distance entre le point et tous les trois centroïdes,
### c) 
> la moyenne des points de chaque cluster est calculée et considérée comme nouveau centroïde;
Dans l’opération reduce vous recevez en paramètre comme clé la valeur de centroïde de cluster et comme valeur une liste des point appartenant à ce cluster. Puis vous calculer la nouvelle valeur de centroïde qui représente la moyenne des point du cluster.
### d) 
> les étapes b) et c) sont répétées jusqu'à ce que les centroïdes ne bougent plus ou le nombre maximum d'itérations a été atteint.

![image](https://github.com/Dembelinho/kmeans_clustering_hdfs_MapReduce/assets/110602716/ee9529f9-f876-4228-aa07-7cd70b82425e)


## Enregistrement dans cashFile
Pour enregistrer le fichier des centres dans le dossier cashFile, nous devons utiliser la commande suivante
`hadoop fs -copyFromLocal centers /`

ou bien 
```
 Configuration configuration=new Configuration();
 Job job=Job.getInstance(configuration);
 job.addCacheFile(new URI("/centroids"));
```

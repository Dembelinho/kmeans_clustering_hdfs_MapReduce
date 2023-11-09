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

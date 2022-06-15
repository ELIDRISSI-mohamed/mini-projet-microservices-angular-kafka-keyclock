<center><h1>Compte Rendu Projet Systèmes Distribués</h1></center>
<h1>Objectif</h1>
<p>L’objectif est de créer un système distribué basé sur les micro-services permettant de gérer les factures 
des clients en utilisant la même architecture que vous avez déjà développé auparavant en y intégrant 
un système de sécurité basé sur Keycloak, Un Bus de messagerie avec KAFKA, un service de Stream 
processing avec Kafka Streams et un service de Batch Processing avec Spring Batch.</p>
<h1>La communcation entre les microservices</h1>
<p>Pour la communication entre les microservice on a deux manières pour le faire soit d'une maniere synchrone avec 
@OpenFeign mais cette communication n'est pas performant. Aussi il existe un autre modele de communication  asynchrone baser sur les evenements 
alors il necessite la mise en place d'un bus d'evenements a travers des brokers comme kafka, rabbitmq ou activemq.
Pour conculre dans ce mni-projet baser sur la communication asynchrome avec le broker kafka.</p>
<h1>Port de chaque microservice</h1>
<ul>
    <li>customer-service ==> 8081 ==> basé sur les communication synchrone.</li>
    <li>billing-service ==> 8082 ==> basé sur les communication synchrone.</li>
    <li>product-service ==> 8083</li>
    <li>inventry-service ==> 8084</li>
    <li>gatway ==> 8085</li>
    <li>producer-service ==> 8087</li>
    <li>consumer-service ==> 8088</li>
    <li>billing-service-kafka ==> 9090 ==> Communication asynchrone</li>
    <li>customer-service-kafka ==> 9091 ==> 9090 ==> Communication asynchrone</li>
</ul>
<h2>1. Mettre en place les micro-services</h2>
<ul>
    <li>a. Customer-Service</li>
    <li>b. Inventory-Service</li>
    <li>c. Billing-Service</li>
    <li>d. Eureka Discovery Service</li>
    <li>e. Spring Cloud Gateway</li>
    <li>f. Product-Service</li>
    <img src="screens/1.JPG" /><br><br>
</ul>
<h2>2. Mise en place du service de Sécurité avec Keycloak</h2>
<ul>
    <li>Mettre en place le serveur d’authentification OAuth2 Keycloak version 12.0.1</li>
    <img src="screens/2.JPG" /><br><br>
    <li>Créer un Realm</li>
    <img src="screens/3.JPG" /><br><br>
    <li>Le client à sécuriser en mode public client</li>
    <img src="screens/4.JPG" /><br><br>
    <li>Créer les rôles (USER, ADMIN, PRODUCT_MANAGER, CUSTOMER_MANAGER et BILLING_MANAGER) </li>
    <img src="screens/5.JPG" /><br><br>
    <li>Créer quelques utilisateurs, Affecter les rôles aux utilisateurs</li>
    <img src="screens/6.JPG" /><br><br>
    <li>Personnaliser le paramétrage des timeout des tokens</li>
    <img src="screens/7.JPG" /><br><br>
    <span>Refresh token : 30min</span>
    <span>Access token : 20min</span>
</ul>
<h2>3. Sécurité l’ensemble des micro-services fonctionnels en mode Bearer-Only</h2>
<ul>
    <li>Configuration real pour application.proprties</li>
    <img src="screens/8.JPG" /><br><br>
    <li>Keycloak configuration</li>
    <img src="screens/9.JPG" /><br><br>
</ul>
<h2>4. Développer une application Web Front End</h2>
<h4>Pour l'application web j'ai permis d'utilisé le framawork de javascript Angular</h4>
<br>
<h6>ROLE_CUSTOMER_SERVICE</h6>
<ul>
    <li>Configuration keycloak</li>
    <img src="screens/10.JPG" /><br><br>
    <li>Home page</li>
    <img src="screens/11.JPG" /><br><br>
    <li>Login page</li>
    <img src="screens/12.JPG" /><br><br>
    <li>Home apres sign-in par un customer utilisateur</li>
    <img src="screens/13.JPG" /><br><br>
    <li>Résultat de la cConsulation de la page produit par un customer</li>
    <img src="screens/erreur.JPG" /><br><br>
    <li>Page customer</li>
    <img src="screens/15.JPG" /><br><br>
    <li>Ajouter des produit au panier</li>
    <img src="screens/16.JPG" /><br><br>
    <span>Apès l'achat la quantité de chaque produit a été modifie</span>
    <li>Générer et imprimer la facture</li>
    <img src="screens/16-1.JPG" /><br><br>
    <li>Facture format PDF</li>
    <img src="screens/facture-pdf.JPG" /><br><br>
</ul>
<h6>ROLE_PRODUCT_SERVICE</h6>
<ul>
    <li>Interface product</li>
    <img src="screens/17.JPG" /><br><br>
    <li>Ajouter un produit 5</li>
    <img src="screens/18.JPG" /><br><br>
    <li>Modifié les information de produit 1</li>
    <img src="screens/19.JPG" /><br><br>
    <li>Supprimé le produit 3</li>
    <img src="screens/20.JPG" /><br><br>
</ul>
<h6>ROLE_ADMIN</h6>
<ul>
    <li>Consulter toutes les factures</li>
    <img src="screens/33-ADMIN.JPG" /><br><br>
</ul>
<h2>5. Sécuriser l’application FrontEnd en mode public client</h2>
<img src="screens/10.JPG" /><br><br>
<h2>6. Personnaliser la sécurité de la partie frontend</h2>
<ul>
    <li>Auto-inscription des utilisateurs</li>
    <img src="screens/23.JPG" /><br><br>
    <li>Politique des mots de passe</li>
    <img src="screens/21.JPG" /><br><br>
    <li>Double authentification OTP</li>
    <img src="screens/22.JPG" /><br><br>
</ul>
<h2>7. Mise en place d’une solution de messagerie asynchrone avec le Broker KAFKA</h2>
<ol>
    <li>Mettre en place le Broker KAFKA</li> 
    <img src="screens/24.JPG" /><br><br>
    <li>Broker KAFKA permet d’envoyer à un tompic « FACTURATION »</li> 
    <ul>
        <li>Configuration application.properties de Producer</li>
        <img src="screens/25.JPG" /><br><br>
        <li>La Création des factures aléatoirement et de les envoyés au Broker Kafka chaque seconde</li>
        <img src="screens/26.JPG" /><br><br>
        <li>Run Producer</li>
        <img src="screens/27.JPG" /><br><br>
        <img src="screens/29.JPG" /><br><br>
    </ul>
    <li>consommer les messages du Topic « FACTURATION »</li>
    <ul>
        <li>Consumer Deserializer</li>
        <img src="screens/28-1.JPG" /><br><br>
        <li>Lire les messages et les enregistrer dans BD et  dans un fichier CSV</li>
        <img src="screens/31.JPG" /><br><br>
        <li>Base de données</li>
        <img src="screens/30.JPG" /><br><br>
        <li>Fichier txt</li>
        <img src="screens/32.JPG" /><br><br>
        <li>Une API REST qui permet de consulter les factures</li>
        <img src="screens/33-ADMIN.JPG" />
    </ul>
</ol>
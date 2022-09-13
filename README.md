# Autoskola - Web aplikacija 
Web aplikacije se može pokrenuti na dva načina, jedan je lokalno pomoću razvojnih programa Angular, MySQL, Spring, a drugi je pomoću Dockera i Kubernetesa
## Upute za pokretanje pomoću IDE alata (Spring, Angular, MySQL)
### Potrebni alati za pokretanje
* STS - Spring Tool Suite
* Angular CLI: 12.2.13
* Node: 12.16.0
* Package Manager: npm 6.13.4
* Java 11
* MySQL Workbrench
* MySQL Server
### Pokretanje baze 
Pomoću MySQL Workbrencha potrebno je spojiti na lokalni MySQL server te uvesti (eng. import) dump baze podataka koji se nalazi u mapi DB_SQL
### Pokretanje servera
* Pokrenuti STS
* Uvesti (eng. import) projekt autoskola-central-back
* U src/main/resources/application.yml zakomentirati linije url, username i password
* U src/main/resources/application.properties odkomentirati tri linije koda ispod komentara Local MySQL Database, a to su spring.datasource.url, spring.datasource.username i spring.datasource.password
* Provjeriti dali se te varijable poklapaju s Vašom bazom podatka (url, username, password)
* Pokrenuti projekt ( jedan od načina desni klik na projekt Run As -> Spring Boot App)
### Pokretanje Forntenda
Potrebno je odkomenti dvije linije ispod komentara "URL za local IDE test" i zakomentirati divije linije ispod komentra  "URL za Kubernetes"
Pokretanje se vrsi pomocu komandne linije (CMD-a)
* Pokrenuti CMD 
* Smjestiti se u direktorij autoskola-central-front (npr. cd C:/Users/example/Desktop/autoskola-central-back) i izvršiti sljedeće linije u CMD-u:
1. npm instal
2. ng s

### Testiranje
Nakon ovih koraka može se pristupiti frontendu linkom na http://localhost:4200/, a backendu http://localhost:8080/

## Upute za pokretanje pomoću Dockera i Kubernetesa
### Potrebni imati
* STS - Spring Tool Suite
* Angular CLI: 12.2.13
* Node: 12.16.0
* Package Manager: npm 6.13.4
* Java 11
* MySQL Workbrench
* MySQL Server
* Docker
* kubectl command line tool
* Ukljucen Kubernetes na Dockeru

### Pokretanje baze 
1. Pokrenuti CMD kao administrator
2. Preuzeti sliku (eng. image) MySQL (naredba: docker pull mysql:5.7)
3. Smjestiti se u direktorij resources u autoskola-central-back (primjer naredbe: cd C:/**/**/autoskola-central-back/src/main/resources)
4. Postaviti potrebne tajne (eng. secrets) i konfiguracije (eng. config map) naredbama : kubectl apply -f mysql-configmap.yml, kubectl apply -f mysqldb-credentials.yml, mysqldb-root-credentials.yml
5. Pokrenuti servis (eng Service), Deployment i PresistentVolumeChain naredbom kubectl apply -f mysql-deployment.yml
6. Vidjeti naziv poda i jel je u statusu Running (naredba: kubectl get pods)
7. Ako je u redu, naredbom kubectl port-forward pod/${naziv_poda} 3306:3306 (ukoliko je port zauzet moze se postaviti drugi port ili ugasiti MySQL server koji koristi taj port)
8. Pristupiti bazi podataka pomocu MySQL Workbrencha podacima hostname:localhost, port:3306, username:root, password:root
9. Uvesti (eng. Import) podatke koje se nalaze u DB_SQL mapi

### Pokretanje servera
Napomena : Ovdje ne treba ništa komentirati/odkomentirati
1. Učitati projekt u STS te napraviti Maven Build (pod Goals staviti clean install)
2. Pokrenuti CMD kao administrator
3. Smjestiti se u mapu autoskola-central-back (primjer naredbe: cd C:/**/**/autoskola-central-back)
4. Napraviti docker konenjer (naredba docker build -t autoskola-central-back:1.0 . ) ne zaboraviti točku na kraju
5. Premjestiti se u mapu resources (naredba cd src/main/resources)
6. Izvršiti naredbu: kubectl apply -f deployment.yml
7. Provjeriti nakon nekog vremena jel ispravno radi pod naredbama "kubectl get pods" i "kubectl logs ${pod_name}
8. kubectl port-forward $pod_name 30163:8080

### Pokretanje Forntenda
1. Pokrenuti CMD kao administrator
2. Smjestiti se u direktoriji autoskola-central-front (primjer naredbe: cd C:/**/**/autoskola-central-front)
3. Izvršiti naredbu "npm run build"
4. Napraviti docker konetnjer, naredba "docker build -t autoskola-central-front:1.0 ." Napomena: ne zaboravit točku na kraju
5. Izvršiti naredbu "kubectl apply -f autoskola-central-front.yml
6. Provjeriti jel pod radi (kubectl get pods) 
7. minikube service autoskola-central-front

### Testiranje
Ako je sve ispravno frontend se može pristupiti poveznicom http://localhost:31000 a server http://localhost:30163

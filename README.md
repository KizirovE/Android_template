**Собрал для себя базовую архитектуру проекта**

Android temaplate project made with:  
**gradle kts**  
**ui** - jetpack compose https://developer.android.com/develop/ui/compose/tutorial  
navigation - voyager https://voyager.adriel.cafe/  
**DI** - koin https://insert-koin.io/  
**network** - ktor https://ktor.io/  
**storage** - paper https://github.com/pilgr/Paper Paper is a fast NoSQL-like storage for Java/Kotlin objects on Android with automatic schema migration support    
**storage** - room DataBase https://developer.android.com/training/data-storage/room  
**serialization** - kotlinx https://github.com/Kotlin/kotlinx.serialization  

**Module arch**  
├──app  
├──core  
│   ├── core  
│   ├── views  
│   ├── theme  
├── domain  
│   ├── domainName1  
│   ├── domainName2  
├── data  
│   ├── dataName1  
│   ├── dataName2  
├── feature  
│   ├── featureName1  
│   ├── featureName2 

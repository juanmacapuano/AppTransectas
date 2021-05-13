# AppTransectas

Es una app para registrar la prospección en el campo recolectando datos.
La estructura general consiste en Proyectos, Transectas, Muestreos y Hallazgos Faunísticos.
Todos los objetos que componen la estructura tienen sus ABM y persistencia en Room Database.
Se utiliza un ViewModel para hacer de intermediario entre las vistas y el repositorio. Este último contiene los DAO´s correspondientes a cada entidad que se debe persistir en la DB.
Se utiliza LiveData para observar los listados de Proyectos, Muestreos y Transectas. 
Este proyecto debería ser refactorizado utilizando un patrón de diseño y buenas prácticas dado que fue utilizado para emplear los primeros conocimientos sobre Android. 

Capturas de Pantalla:

![alt text](https://i.imgur.com/I1qaYzt.png) ![alt text](https://i.imgur.com/KmJY3RL.png)
![alt text](https://i.imgur.com/qZ9OQD0.png) ![alt text](https://i.imgur.com/FvpO92J.png)
![alt text](https://i.imgur.com/gp0KX2W.png) ![alt text](https://i.imgur.com/fyTMaOF.png)
![alt text](https://i.imgur.com/lQoY9KL.png) ![alt text](https://i.imgur.com/2ZWQuUH.png)
![alt text](https://i.imgur.com/hmOZPSh.png) ![alt text](https://i.imgur.com/iB8lfwK.png)

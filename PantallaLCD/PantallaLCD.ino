// incluimos la libreria LCD
#include <LiquidCrystal.h>

// inicializamos los pines 
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);

//se inicializan las variables
/*como java envia carater por caracter, lo que realizael IDE es concatenar 
cada carater para mostrar lo que envio Java desde la interfaz*/
String msg="";
//variable que se envia desde la interfaz
char input;
//Entrada analogica de LM35
int Ana1 = A0; 
int Temp = 0;
char Grados = 'C';
 
void setup() {
  //Iniciamos la comunicación con el puerto serie
  lcd.begin(16, 2);                
  pinMode(10,OUTPUT);     
  //Cofiguramos el tamaño de la LCD
  Serial.begin(9600);
}

void loop() {
  //En caso que haya información en el Serial Port, se entra en esta estructura
  if (Serial.available()) {
    input = Serial.read();
    msg += input;
    if(input == '2'){
        lcd.clear();
		//Leemos el valor de la entrada analogica 
        Temp = analogRead(Ana1); 
        // Primero transformamos la lectura analógica de tensión a un valor de temperatura
        Temp = (5.0 * Temp * 100.0)/1024.0;
		//Mostramos los grados en el serial
        Serial.print("Grados: ");
        Serial.print(Temp);
        Serial.print(Grados);
        Serial.println("C");
        
        //Mostramos los grados en la pantalla LCD
		//Con este comando decimos en que linea queremos escribir
        lcd.setCursor(1,0); 
        lcd.print("Temp: ");
        lcd.print(Temp);
        lcd.print(" C");
		//Al ser temperatura no hace falta leerlo tan seguido
        delay(1000); 
        msg="";
    } else {
		//se limpia la pantalla  y se muestra el mensaje enviado de Java
          lcd.clear();
          lcd.print(msg); 
        }
    }
}


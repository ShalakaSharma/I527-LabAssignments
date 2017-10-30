// include the library code:
#include <LiquidCrystal.h>
#include <string.h>
//#include<serLCD.h>
#define MAX_VAL 16
#define LCD_Backlight 3
// initialize the library by associating any needed LCD interface pin
// with the arduino pin number it is connected to
const int rs = 12, en = 11, d4 = 5, d5 = 4, d6 = 3, d7 = 2;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);
char input_key[]={'0','1','2','3','4','5','6','7','8','9','+','-','*','/', 'S'};
const int delete_button = 9;     // the number of the pushbutton pin
const int enter_button = 8;     // the number of the pushbutton pin
int button_state_delete = HIGH;         // variable for reading the pushbutton status
int button_state_enter = HIGH;         // variable for reading the pushbutton status
int i=15;
int j=0;
char oper = ' ';
int op1,op2,op3;
String expression;
boolean displayAns = false;
String expression_buffer = "";
boolean pendingCalc = false;
boolean op3_flag = false;
char oper_prev = ' ';
int converter = 1024/14;

void setup() {
  // set up the LCD's number of columns and rows:
  lcd.begin(16, 2);
  lcd.clear();
  pinMode(delete_button,INPUT);
  pinMode(enter_button,INPUT);
  // turn on the cursor:
  lcd.cursor();
  lcd.setCursor(i,1);
  lcd.blink();
  lcd.leftToRight();
  Serial.begin(9600);
}


void loop() {
 int sensorValue = analogRead(A0);
 int input = sensorValue/converter;
 lcd.print(input_key[input]);
 lcd.setCursor(i,1);
// Serial.print("Input:");
// Serial.println(input);
  //Enter button
 if(digitalRead(enter_button) == LOW && button_state_enter == HIGH){
    Serial.println("ENTER PRESSED");
    button_state_enter = LOW;
    
    if(input >=0 && input <=9) {
      expression += String(input_key[input]);
      lcd.setCursor(i-expression.length(),1);
      String expression_to_print_to_lcd = expression + " ";
      lcd.print(expression_to_print_to_lcd);
      Serial.print("expression:");
      Serial.println(expression_to_print_to_lcd);
    } else if(input >=10 && input<=13) {
      Serial.print("current calc flag was:");
      Serial.println(pendingCalc);
      if(!pendingCalc) {
        Serial.println("in not pendingCalc:");
        oper = char(input_key[input]);
        Serial.print("operator:");
        Serial.println(oper);
        op1 = expression.toInt();
        Serial.print("operand 1:");
        Serial.println(op1);
        expression_buffer +=  expression + " " + oper + " ";
        Serial.print("Expression buffer:");
        Serial.println(expression_buffer);
        lcd.setCursor(i-expression_buffer.length(),0); //print to upper line
        lcd.print(expression_buffer);
        lcd.setCursor(0,1); // clear lower line
        lcd.print("                ");
        lcd.setCursor(i,1);
        expression = "";
        pendingCalc = true;
      } else {
        Serial.println("in pendingCalc:");
        // find op2
        op2 = expression.toInt();
        char new_oper = char(input_key[input]);
        // compute ans -- op1 oper op2
        int ans;
        if((!op3_flag && (oper == '+' || oper == '-')) && (new_oper == '*'  || new_oper == '/')){
          Serial.println("bodmas logic:");
          op3 = op1;
          oper_prev = oper; 
          op1 = op2;  
          op3_flag = true;
          Serial.print("pending calc flag was:");
          Serial.println(pendingCalc);
          pendingCalc = true;
          Serial.print("pending calc flag is:");
          Serial.println(pendingCalc);
          Serial.print("oper_prev:");
          Serial.println(oper_prev);
          Serial.print("operand3:");
          Serial.println(op3);
          Serial.print("new operand1:");
          Serial.println(op1);
        } else {
          Serial.println("no bodmas logic required here:");
          if(oper == '/' && op2 == 0) {
            Serial.print("Invalid Operation. / by 0");
            lcd.clear();
            lcd.setCursor(0,0);
            lcd.print("INVALID. / BY 0");
            delay(1000);
            op3_flag = false;
            oper_prev = ' ';
            op3 = 0;
            lcd.setCursor(0,0); // clear upper line
            lcd.print("                ");
            lcd.setCursor(0,1); // clear lower line
            lcd.print("                ");
            lcd.setCursor(i,1);
            expression = "";
            pendingCalc = false;
            expression_buffer = "";
            return;
          }
          if(oper == '+') {
            ans = op1+op2;
            Serial.print("ans:");
            Serial.println(ans);
          } else if(oper == '-'){
            ans = op1-op2;
            Serial.print("ans:");
            Serial.println(ans);
          } else if(oper == '*'){
            ans = op1*op2;
            Serial.print("ans:");
            Serial.println(ans);
          } else if(oper == '/'){
            
            ans = op1/op2;
            Serial.print("ans:");
            Serial.println(ans);
          }
          String answer_string = String(ans);
        
          // set op1 to ans
          op1 = ans;
          Serial.print("operand 1:");
          Serial.println(op1);
          pendingCalc = true;
          
         }
          // set oper to new oper
          oper = new_oper;
          // update buffer
          expression_buffer +=  expression + " " + oper + " ";
          Serial.print("Expression buffer:");
          Serial.println(expression_buffer);
          lcd.setCursor(i-expression_buffer.length(),0); //print to upper line
          lcd.print(expression_buffer);
          lcd.setCursor(0,1); // clear lower line
          lcd.print("                ");
          lcd.setCursor(i,1);
          expression = "";
          
      }
    } else if(input == 14) {
      op2 = expression.toInt();
      Serial.print("operand 1:");
      Serial.println(op1);
      Serial.print("operator:");
      Serial.println(oper);
      Serial.print("operand 2:");
      Serial.println(op2);
      int ans;
      if(oper == '/' && op2 == 0) {
        Serial.print("Invalid Operation. / by 0");
        lcd.clear();
        lcd.setCursor(0,0);
        lcd.print("INVALID. / BY 0");
        delay(1000);
        op3_flag = false;
        oper_prev = ' ';
        op3 = 0;
        lcd.setCursor(0,0); // clear upper line
        lcd.print("                ");
        lcd.setCursor(0,1); // clear lower line
        lcd.print("                ");
        lcd.setCursor(i,1);
        expression = "";
        pendingCalc = false;
        expression_buffer = "";
        return;
      }
      if(oper == '+') {
        ans = op1+op2;
        Serial.print("ans:");
        Serial.println(ans);
      } else if(oper == '-'){
        ans = op1-op2;
        Serial.print("ans:");
        Serial.println(ans);
      } else if(oper == '*'){
        ans = op1*op2;
        Serial.print("ans:");
        Serial.println(ans);
      } else if(oper == '/'){
        ans = op1/op2;
        Serial.print("ans:");
        Serial.println(ans);
      }
      if(op3_flag) {
        if(oper_prev == '+') {
           ans += op3;
        } else if(oper_prev == '-') {
          ans = op3 - ans;
        }
       
      } 
      op3_flag = false;
      oper_prev = ' ';
      op3 = 0;
      String answer_string = String(ans);
      Serial.print("ans_string:");
      Serial.println(answer_string);
      lcd.setCursor(0,0); // clear upper line
      lcd.print("                ");
      lcd.setCursor(0,1); // clear lower line
      lcd.print("                ");
      lcd.setCursor(i-answer_string.length(),1); // write ans to lower line
      lcd.print(answer_string);
      lcd.setCursor(i,1);
      expression = answer_string;
      pendingCalc = false;
      expression_buffer = "";
    }
 }
 else if (digitalRead(enter_button) == HIGH && button_state_enter == LOW)
 {
    Serial.println("ENTER RELEASED");
    button_state_enter = HIGH;
 }
 
 //Delete button
 if(digitalRead(delete_button) == LOW && button_state_delete == HIGH){
    Serial.println("DELETE PRESSED");
    button_state_delete = LOW;
    int len = expression.length();
    if(len>0) {
      expression = expression.substring(0,len-1);
      lcd.setCursor(0,1); // clear lower line
      lcd.print("                ");
      lcd.setCursor(i-expression.length(),1); // write new expression to lower line
      lcd.print(expression);  
    } else {
      Serial.println("Nothing to delete");
    }
 } else if (digitalRead(delete_button) == HIGH && button_state_delete == LOW){
    Serial.println("DELETE RELEASED");
    button_state_delete = HIGH;
 }
  delay(1000);
}

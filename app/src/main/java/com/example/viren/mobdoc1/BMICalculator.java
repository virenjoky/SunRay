package com.example.viren.mobdoc1;

/**
 * Created by Viren on 04/07/2017.
 */

public class BMICalculator {


        public static int getHeartPercentage(int age, int weight,float height,char Gender,int stepCount)
        {
            //int age=20,  weight=50;
            float bmi=0;
            //char Gender='F';
            int hrt_pcnt =0;
            //float height=160, heightmet=0;
            //heightmet = height/100;
            bmi = weight / (height * height);

            if(age >= 70 & age <90 & Gender == 'M')
            {
                if (bmi <15)
                {

                    hrt_pcnt = 50;
                }
                else
                if (bmi >15 & bmi <= 18.5)
                {
                    hrt_pcnt = 52;
                }
                else
                if (bmi > 18.5 & bmi < 25)
                {
                    hrt_pcnt = 55;
                }
                else
                if (bmi > 25 & bmi <= 30)
                {
                    hrt_pcnt = 40;
                }
                else
                if (bmi > 30 & bmi < 40)
                {
                    hrt_pcnt = 35;
                }
                else
                if (bmi > 40)
                {
                    hrt_pcnt = 30;
                }
            }
            else if(age >= 50 & age <69 & Gender == 'M')
            {
                if (bmi <15)
                {

                    hrt_pcnt = 55	;
                }
                else
                if (bmi >15 & bmi <= 18.5)
                {
                    hrt_pcnt = 58	;
                }
                else
                if (bmi > 18.5 & bmi < 25)
                {
                    hrt_pcnt = 60;
                }
                else
                if (bmi > 25 & bmi <= 30)
                {
                    hrt_pcnt = 45;
                }
                else
                if (bmi > 30 & bmi < 35)
                {
                    hrt_pcnt = 40;
                }
                else
                if (bmi > 35 & bmi <= 40)
                {
                    hrt_pcnt = 35;
                }
                else if (bmi > 40)
                {
                    hrt_pcnt = 30;
                }


            }
            else if(age >= 40 & age <50 & Gender == 'M')
            {
                if (bmi <15)
                {

                    hrt_pcnt = 60	;
                }
                else
                if (bmi >15 & bmi <= 18.5)
                {
                    hrt_pcnt = 65	;
                }
                else
                if (bmi > 18.5 & bmi < 25)
                {
                    hrt_pcnt = 70;
                }
                else
                if (bmi > 25 & bmi <= 30)
                {
                    hrt_pcnt = 50;
                }
                else
                if (bmi > 30 & bmi < 35)
                {
                    hrt_pcnt = 40;
                }
                else
                if (bmi > 35 & bmi <= 40)
                {
                    hrt_pcnt = 30;
                }
                else if (bmi > 40)
                {
                    hrt_pcnt = 30;
                }


            }
            else if(age >= 30 & age <40 & Gender == 'M')
            {
                if (bmi <15)
                {

                    hrt_pcnt = 62	;
                }
                else
                if (bmi >15 & bmi <= 18.5)
                {
                    hrt_pcnt = 65	;
                }
                else
                if (bmi > 18.5 & bmi < 25)
                {
                    hrt_pcnt = 75;
                }
                else
                if (bmi > 25 & bmi <= 30)
                {
                    hrt_pcnt = 50;
                }
                else
                if (bmi > 30 & bmi < 35)
                {
                    hrt_pcnt = 40;
                }
                else
                if (bmi > 35 & bmi <= 40)
                {
                    hrt_pcnt = 30;
                }
                else if (bmi > 40)
                {
                    hrt_pcnt = 30;
                }


            }
            else if(age >= 20 & age <30 & Gender == 'M')
            {
                if (bmi <15)
                {

                    hrt_pcnt = 70	;
                }
                else
                if (bmi >15 & bmi <= 18.5)
                {
                    hrt_pcnt = 75	;
                }
                else
                if (bmi > 18.5 & bmi < 25)
                {
                    hrt_pcnt = 80;
                }
                else
                if (bmi > 25 & bmi <= 30)
                {
                    hrt_pcnt = 65;
                }
                else
                if (bmi > 30 & bmi < 35)
                {
                    hrt_pcnt = 55;
                }
                else
                if (bmi > 35 & bmi <= 40)
                {
                    hrt_pcnt = 50;
                }
                else if (bmi > 40)
                {
                    hrt_pcnt = 40;
                }


            }
            else if(age < 20 & Gender == 'M')
            {
                if (bmi <15)
                {

                    hrt_pcnt = 72	;
                }
                else
                if (bmi >15 & bmi <= 18.5)
                {
                    hrt_pcnt = 76	;
                }
                else
                if (bmi > 18.5 & bmi < 25)
                {
                    hrt_pcnt = 80;
                }
                else
                if (bmi > 25 & bmi <= 30)
                {
                    hrt_pcnt = 70;
                }
                else
                if (bmi > 30 & bmi < 35)
                {
                    hrt_pcnt = 65;
                }
                else
                if (bmi > 35 & bmi <= 40)
                {
                    hrt_pcnt = 55;
                }
                else if (bmi > 40)
                {
                    hrt_pcnt = 40;
                }


            }
            if(age >= 70 & age <90 & Gender == 'F')
            {
                if (bmi <15)
                {

                    hrt_pcnt = 48;
                }
                else
                if (bmi >15 & bmi <= 18.5)
                {
                    hrt_pcnt = 50;
                }
                else
                if (bmi > 18.5 & bmi < 25)
                {
                    hrt_pcnt = 53;
                }
                else
                if (bmi > 25 & bmi <= 30)
                {
                    hrt_pcnt = 38;
                }
                else
                if (bmi > 30 & bmi < 40)
                {
                    hrt_pcnt = 35;
                }
                else
                if (bmi > 40)
                {
                    hrt_pcnt = 30;
                }
            }
            else if(age >= 50 & age <69 & Gender == 'F')
            {
                if (bmi <15)
                {

                    hrt_pcnt = 53	;
                }
                else
                if (bmi >15 & bmi <= 18.5)
                {
                    hrt_pcnt = 55	;
                }
                else
                if (bmi > 18.5 & bmi < 25)
                {
                    hrt_pcnt = 60;
                }
                else
                if (bmi > 25 & bmi <= 30)
                {
                    hrt_pcnt = 42;
                }
                else
                if (bmi > 30 & bmi < 35)
                {
                    hrt_pcnt = 38;
                }
                else
                if (bmi > 35 & bmi <= 40)
                {
                    hrt_pcnt = 35;
                }
                else if (bmi > 40)
                {
                    hrt_pcnt = 30;
                }


            }
            else if(age >= 40 & age <50 & Gender == 'F')
            {
                if (bmi <15)
                {

                    hrt_pcnt = 58	;
                }
                else
                if (bmi >15 & bmi <= 18.5)
                {
                    hrt_pcnt = 65	;
                }
                else
                if (bmi > 18.5 & bmi < 25)
                {
                    hrt_pcnt = 70;
                }
                else
                if (bmi > 25 & bmi <= 30)
                {
                    hrt_pcnt = 50;
                }
                else
                if (bmi > 30 & bmi < 35)
                {
                    hrt_pcnt = 38;
                }
                else
                if (bmi > 35 & bmi <= 40)
                {
                    hrt_pcnt = 32;
                }
                else if (bmi > 40)
                {
                    hrt_pcnt = 30;
                }


            }
            else if(age >= 30 & age <40 & Gender == 'F')
            {
                if (bmi <15)
                {

                    hrt_pcnt = 60	;
                }
                else
                if (bmi >15 & bmi <= 18.5)
                {
                    hrt_pcnt = 64	;
                }
                else
                if (bmi > 18.5 & bmi < 25)
                {
                    hrt_pcnt = 72;
                }
                else
                if (bmi > 25 & bmi <= 30)
                {
                    hrt_pcnt = 47;
                }
                else
                if (bmi > 30 & bmi < 35)
                {
                    hrt_pcnt = 40;
                }
                else
                if (bmi > 35 & bmi <= 40)
                {
                    hrt_pcnt = 30;
                }
                else if (bmi > 40)
                {
                    hrt_pcnt = 30;
                }


            }
            else if(age >= 20 & age <30 & Gender == 'F')
            {
                if (bmi <15)
                {

                    hrt_pcnt = 70	;
                }
                else
                if (bmi >15 & bmi <= 18.5)
                {
                    hrt_pcnt = 75	;
                }
                else
                if (bmi > 18.5 & bmi < 25)
                {
                    hrt_pcnt = 80;
                }
                else
                if (bmi > 25 & bmi <= 30)
                {
                    hrt_pcnt = 65;
                }
                else
                if (bmi > 30 & bmi < 35)
                {
                    hrt_pcnt = 55;
                }
                else
                if (bmi > 35 & bmi <= 40)
                {
                    hrt_pcnt = 50;
                }
                else if (bmi > 40)
                {
                    hrt_pcnt = 40;
                }


            }
            else if(age < 20 & Gender == 'F')
            {
                if (bmi <15)
                {

                    hrt_pcnt = 70	;
                }
                else
                if (bmi >15 & bmi <= 18.5)
                {
                    hrt_pcnt = 74	;
                }
                else
                if (bmi > 18.5 & bmi < 25)
                {
                    hrt_pcnt = 78;
                }
                else
                if (bmi > 25 & bmi <= 30)
                {
                    hrt_pcnt = 70;
                }
                else
                if (bmi > 30 & bmi < 35)
                {
                    hrt_pcnt = 65;
                }
                else
                if (bmi > 35 & bmi <= 40)
                {
                    hrt_pcnt = 55;
                }
                else if (bmi > 40)
                {
                    hrt_pcnt = 40;
                }


            }

            System.out.print(hrt_pcnt);

            float newCount=stepCount/10000;
            float newhrtPerct=hrt_pcnt+newCount;

            int  newOne=0;
                    if(Math.round(newhrtPerct)>90){
                        newOne=90;
                    }else{
                        newOne=Math.round(newhrtPerct);
                    }

            return newOne;
    }
}

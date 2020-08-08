
//The Exchanger Class provides a sort of rendezvous point for two threads

import java.util.concurrent.Exchanger;
 
public class ExchangerExample
{
 
        Exchanger exchanger = new Exchanger();
 
        private class Producer implements Runnable
        {
                private String queue;
                @Override
                public void run()
                {
                    try
                    {
                             queue = (String) exchanger.exchange("Ready Queue");
                             System.out.println(Thread.currentThread().getName()+" now has "+queue);
                    }
                    catch (InterruptedException e)
                    {
                            e.printStackTrace();
                    }
                }
        }
 
        private class Consumer implements Runnable
        {
 
                private String queue;
                @Override
                public void run()
                {
                    try
                    {
                            queue = (String) exchanger.exchange("Empty Queue");
                            System.out.println(Thread.currentThread().getName()+" now has "+queue);
                    }
                    catch (InterruptedException e)
                    {
                            e.printStackTrace();
                    }
                }
        }
        
        private void demo()
        {
                new Thread(new Producer(),"Producer").start();
                new Thread(new Consumer(),"Consumer").start();
        }
 
        public static void main(String[] args)
        {
                new ExchangerExample().demo();
        }
}

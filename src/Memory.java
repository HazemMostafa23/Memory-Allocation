/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryalloc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Zemzem
 */
public class Memory {
    public class rank{
        String name;
        int rank;
        
        
    }
    int n; int count; int avg; int freeholescount=0;
    int partsum=0; 
    int partitions[]=new int[20];
    int partitions2[];
 int processes[]=new int[20];
    int block[]=new int[20];
    ArrayList<Integer> unallocated;
    Scanner input=new Scanner(System.in);
    HashMap<String, Integer> avgscore = new HashMap<String, Integer>();
    
    
  rank list[]=new rank[3];
    
    boolean flag[];
 Memory(){
     for(int i=0;i<3;i++){
         list[i]=new rank();
         
     }
  list[0].name="firstfit";
  list[1].name="bestfit";
  list[2].name="worstfit";
     
 }
    void start() throws FileNotFoundException{
          System.out.println("Press 1 to Enter inputs or press any other number to read from file");
          int y;
            while (!input.hasNextInt()) {
        System.out.println("That's not a number,please enter a number.");
        input.next();
    }
         
    y = input.nextInt();
    if(y==1){
             input();
    } else{
              file();
          }
    
        
        
    }
    void file() throws FileNotFoundException{
        n=0; count=0; 
        partitions=new int[20];
        processes=new int[20];
        System.out.println("Please enter the filename(the path too) which contains the partitions.(WITHOUT THE FORMAT,ALREADY WRITTEN)");
        String partname;
        partname=input.next();
        File file = new File(partname+".txt"); 
        while(!file.exists()){
             System.out.println("The file doesn't exist , Please enter a valid file name");
            partname=input.next();
            file=new File(partname+".txt");
        }
        Scanner sc = new Scanner(file); 
       
          while(sc.hasNext())
  {
      partitions[count]=sc.nextInt();
      count++;
      
  }
          System.out.println("Please enter the filename(the path too) which contains the processes.(WITHOUT THE FORMAT,ALREADY WRITTEN)");
        String processname;
        processname=input.next();
        File file2=new File(processname+".txt");
         while(!file2.exists()){
             System.out.println("The file doesn't exist , Please enter a valid file name");
               processname=input.next();
            file2=new File(processname+".txt");
             
         } 
    Scanner sc2=new Scanner(file2);
  while(sc2.hasNext()){
      processes[n]=sc2.nextInt();
  n++;
  }

 
        
    }
    void input(){
         System.out.println("Enter no of processes: ");
                      n=input.nextInt();
                      System.out.println("Enter processes size in KBs");
                      for(int i=0;i<n;i++){
                          processes[i]=input.nextInt();
                          }
                      System.out.println("Enter no of partitions");
                      count=input.nextInt();
                        System.out.println("Enter partitions size in KBs");
                      for(int i=0;i<count;i++){
                          partitions[i]=input.nextInt();
                          
                      }
                      
        
    }
    
    
    int FirstFit() throws FileNotFoundException
    {
System.out.println("WELCOME TO FIRST FIT ALGORITHM"); 
partitions2=new int[20];
int processes2[]=new int[20];
for(int i=0;i<count;i++){
partitions2[i]=partitions[i];
}
for(int i=0;i<n;i++){
processes2[i]=processes[i];
}
flag=new boolean[20];
unallocated=  new ArrayList<Integer>();
for(int i=0;i<n;i++){
for(int j=0;j<count;j++){
if(processes2[i]<=partitions2[j]){
partitions2[j]=partitions2[j]-processes2[i];   
processes2[i]=0;
if(processes2[i]<partitions2[j]){
flag[i]=true;}}}}
boolean check=true;
for(int i=0;i<n;i++){
if(processes2[i]!=0){
unallocated.add(processes2[i]);
check=false;
}}
if(check==false){
System.out.println("All processes are located except: ");
for(int i=0;i<n;i++){
if(processes2[i]!=0){
System.out.println("The process with size " + processes2[i]+" coudn't be allocated");}}}
else System.out.println("All processes are located successfully");
for(int i=0;i<count;i++){
System.out.println("Remaining free holes of  partition " + i + " of size: " + partitions[i] + " is equal to " + partitions2[i]);   
}
avgscore.put("firstfit", avg());
if(check==false){
partsum=0;
int unindex=0;
int unallocatedsum=0;
for(int i=0;i<count;i++){
partsum=partsum+partitions2[i];
}
for(int i=0;i<unallocated.size();i++){
unallocatedsum=unallocatedsum+unallocated.get(i);
} 
if(unallocatedsum<partsum){
System.out.println("There's external fragmentation as the sum of the remaining holes is larger than the unallocated size");
System.out.println("To fix this external fragmentation we could use compaction and allocating those free holes to one large block.");
System.out.println("We should put the process of size: "+ unallocatedsum + " in the new memory partition which is equal to: "+ partsum);
} 
} else System.out.println("There's no external fragmentation");   
int i=0;
while(i<count){
if(flag[i]==true){
System.out.println("There's internal fragmentation as there's left unused holes");
break;
}
i++;
}
return unallocated.size();
}
    
int bestfit() throws FileNotFoundException
{
    partitions2=new int[20];
            int processes2[]=new int[20];
            for(int i=0;i<count;i++){
                partitions2[i]=partitions[i];
                
            }
            for(int i=0;i<n;i++){
                processes2[i]=processes[i];
                
            }
           
 
    
   System.out.println("WELCOME TO BEST FIT ALGORITHM");
unallocated=  new ArrayList<Integer>();
flag=new boolean[20];
int min; int index;
for(int i=0;i<n;i++){
min=999999; index=-1;
for(int j=0;j<count;j++){ 
if(processes2[i]<=partitions2[j]&& min>partitions2[j]){
min=partitions2[j];
index=j;

}

}
if(processes2[i]!=0&& index!=-1){
partitions2[index]=partitions2[index]-processes2[i];
processes2[i]=0;
if(processes2[i]<partitions2[index]){


flag[i]=true;
}

}


}
 boolean check=true;
        for(int i=0;i<n;i++){
            if(processes2[i]!=0){
                unallocated.add(processes2[i]);
                check=false;
                
            }
            
        }
        if(check==false){
 System.out.println("All processes are located except: ");
        for(int i=0;i<n;i++){
            if(processes2[i]!=0){
                System.out.println("The process with size " + processes2[i]+" coudn't be allocated");
                
            }
            
        }
        }
        else System.out.println("All processes are located successfully");
 
        
      for(int i=0;i<count;i++){
System.out.println("Remaining free holes of  partition " + i + " of size: " + partitions[i] + " is equal to " + partitions2[i]); 

}
      
      
     avgscore.put("bestfit", avg());
      
     
         if(check==false){
       partsum=0;
      int unindex=0;
      int unallocatedsum=0;
      for(int i=0;i<count;i++){
          partsum=partsum+partitions2[i];
          
      }
     for(int i=0;i<unallocated.size();i++){
        unallocatedsum=unallocatedsum+unallocated.get(i);
                 
     } 
     if(unallocatedsum<partsum){
      System.out.println("There's external fragmentation as the sum of the remaining holes is larger than the unallocated size");
     System.out.println("To fix this external fragmentation we could use compaction and allocating those free holes to one large block.");
     System.out.println("We should put the process of size: "+ unallocatedsum + " in the new memory partition which is equal to: "+ partsum);
   
       } 
} else System.out.println("There's no external fragmentation");
         
     
          int i=0;
while(i<count){
    if(flag[i]==true){
        System.out.println("There's internal fragmentation as there's left unused holes");
        break;
    }
    i++;
}

        return unallocated.size();
    
}
int worstfit() throws FileNotFoundException{
partitions2=new int[20];
int processes2[]=new int[20];
for(int i=0;i<count;i++){
partitions2[i]=partitions[i];

}
for(int i=0;i<n;i++){
processes2[i]=processes[i];

}


System.out.println("WELCOME TO WORST FIT ALGORITHM");
unallocated=  new ArrayList<Integer>();

int max; int index;
for(int i=0;i<n;i++){
max=0; index=-1;
for(int j=0;j<count;j++){ 
if(processes2[i]<=partitions2[j]&& max<partitions2[j]){
max=partitions2[j];
index=j;

}

}
if(processes2[i]!=0&& index!=-1){
partitions2[index]=partitions2[index]-processes2[i];
processes2[i]=0;
if(processes2[i]<partitions2[index]){

flag[i]=true;

}

}


}
boolean check=true;
for(int i=0;i<n;i++){
if(processes2[i]!=0){
unallocated.add(processes2[i]);
check=false;

}

}
if(check==false){

System.out.println("All processes are located except: ");
for(int i=0;i<n;i++){
if(processes2[i]!=0){
System.out.println("The process with size " + processes2[i]+" coudn't be allocated");

}

}
}
else System.out.println("All processes are located successfully");







for(int i=0;i<count;i++){
System.out.println("Remaining free holes of  partition " + i + " of size: " + partitions[i] + " is equal to " + partitions2[i]
);  

}

avgscore.put("worstfit", avg());





if(check==false){
partsum=0;
int unindex=0;
int unallocatedsum=0;
for(int i=0;i<count;i++){
partsum=partsum+partitions2[i];

}
for(int i=0;i<unallocated.size();i++){
unallocatedsum=unallocatedsum+unallocated.get(i);

} 
if(unallocatedsum<partsum){
System.out.println("There's external fragmentation as the sum of the remaining holes is larger than the unallocated size");
System.out.println("To fix this external fragmentation we could use compaction and allocating those free holes to one large block.");
System.out.println("We should put the process of size: "+ unallocatedsum + " in the new memory partition which is equal to: "+ partsum);


} 
} else System.out.println("There's no external fragmentation");
int i=0;
while(i<count){
if(flag[i]==true){
System.out.println("There's internal fragmentation as there's left unused holes");
break;
}
i++;
}

return unallocated.size();

}

int avg(){
    freeholescount=0;
    partsum=0;
      for(int i=0;i<count;i++){ //caluclate avg
     if(partitions2[i]!=0){
         freeholescount++;
         
     }
     partsum=partsum+partitions2[i];
     }
     if(freeholescount!=0){
    return partsum/freeholescount++;
      }
      else return 0;
}
void compare() throws FileNotFoundException{
System.out.println("Comparing the 3 Algorithms to detect the most efficent one:  ");
      list[0].rank=FirstFit();
      System.out.println("**********************************************************************************************************************************************");
      list[1].rank=bestfit();
      System.out.println("**********************************************************************************************************************************************");
      list[2].rank=worstfit();
      System.out.println("**********************************************************************************************************************************************");
      rank temp;
       System.out.println("Their order by efficiency matter is ");
              if(list[0].rank!=list[1].rank&&list[1].rank!=list[2].rank){
                  for(int i=0;i<3;i++){
                      for(int j=i+1;j<3;j++){
                          if(list[i].rank>list[j].rank){
                              temp=list[i];
                              list[i]=list[j];
                              list[j]=temp;
                          }
                          
                      }
                      
                  }
                    
              for(int i=0;i<3;i++){
                    System.out.println(list[i].name);
                      }
              }
              else if(list[0].rank>list[1].rank&&list[1]==list[2]){ 
                  if(avgscore.get("bestfit")>=avgscore.get("worstfit")){
                     
                              System.out.println(list[1].name+ " \n"+ list[2].name+ " \n" +list[0].name);
                  }
                  else System.out.println(list[2].name+ " \n"+ list[1].name+ " \n" +list[0].name);
                  
                   }
              else if(list[0].rank==list[2].rank&&list[1].rank>list[0].rank){
                   if(avgscore.get("firstfit")>=avgscore.get("worstfit")){
                     
                              System.out.println(list[0].name+ " \n"+ list[2].name+ " \n" +list[1].name);
                  }
                   else System.out.println(list[2].name+ " \n"+ list[0].name+ " \n" +list[1].name);
                  
                  
              }
              else if(list[2].rank>list[0].rank&& list[0].rank==list[1].rank){
                  if(avgscore.get("firstfit")>=avgscore.get("bestfit")){
                     
                              System.out.println(list[0].name+ " \n"+ list[1].name+ " \n" +list[2].name);
                  } else System.out.println(list[1].name+ " \n"+ list[0].name+ " \n" +list[2].name);
                  
              }
              else if(list[0].rank<list[1].rank&&list[1].rank==list[2].rank){
                   if(avgscore.get("bestfit")>=avgscore.get("worstfit")){
                     
                              System.out.println(list[0].name+ " \n"+ list[1].name+ " \n" +list[2].name);
                  } else System.out.println(list[0].name+ " \n"+ list[2].name+ " \n" +list[1].name);
              }
                   else if(list[1].rank<list[2].rank&&list[2].rank==list[0].rank){
                if(avgscore.get("firstfit")>=avgscore.get("worstfit")){
                     
                              System.out.println(list[1].name+ " \n"+ list[0].name+ " \n" +list[2].name);
                  } else System.out.println(list[1].name+ " \n"+ list[2].name+ " \n" +list[0].name);
            
                           
                           }
                   else if(list[2].rank<list[1].rank&&list[1]==list[0]){
                       
                        if(avgscore.get("firstfit")>=avgscore.get("bestfit")){
                     
                              System.out.println(list[2].name+ " \n"+ list[0].name+ " \n" +list[1].name);
                  } else System.out.println(list[2].name+ " \n"+ list[1].name+ " \n" +list[0].name);


                  
                  
              }
                   
                   else if(list[0].rank==list[1].rank&& list[1].rank==list[2].rank){
                       
                       if(avgscore.get("firstfit")>avgscore.get("bestfit")&& avgscore.get("bestfit")>=avgscore.get("worstfit"))
                       {
                           System.out.println(list[0].name+" \n" + list[1].name + " \n"+list[2].name);
                       }
                       else   if(avgscore.get("firstfit")>avgscore.get("worstfit")&& avgscore.get("worstfit")>=avgscore.get("bestfit")){
                              System.out.println(list[0].name+" \n" + list[2].name + " \n"+list[1].name);
                       }
                       else if(avgscore.get("bestfit")>avgscore.get("worstfit")&&avgscore.get("worstfit")>=avgscore.get("firstfit")){
                              System.out.println(list[1].name+" \n" + list[2].name + " \n"+list[0].name);
                         }
                       else if(avgscore.get("bestfit")>avgscore.get("firstfit")&&avgscore.get("firstfit")>=avgscore.get("worstfit")){
                           System.out.println(list[1].name+" \n" + list[0].name + " \n"+list[2].name);

                           
                       } else if(avgscore.get("worstfit")>avgscore.get("firstfit")&&avgscore.get("firstfit")>=avgscore.get("bestfit")){
                           
                           System.out.println(list[2].name+" \n" + list[0].name + " \n"+list[1].name);
                       }
                       else if(avgscore.get("worstfit")>avgscore.get("bestfit")&&avgscore.get("bestfit")>=avgscore.get("firstfit")){
                           
                           System.out.println(list[2].name+" \n" + list[1].name + " \n"+list[0].name);
                       }
                      
                       else if(avgscore.get("firstfit")==avgscore.get("bestfit")&&avgscore.get("bestfit")>avgscore.get("worstfit")){
                           System.out.println(list[0].name +" and "+ list[1].name + " have the same efficieny while " + list[2].name+" has lesser efficieny");
                           
                       }
                       else if(avgscore.get("firstfit")==avgscore.get("worstfit")&&avgscore.get("worstfit")>avgscore.get("bestfit")){
                           System.out.println(list[0].name +" and "+ list[2].name + " have the same efficieny while " + list[1].name+" has lesser efficieny");
                           
                       }   else if(avgscore.get("bestfit")==avgscore.get("firstfit")&&avgscore.get("firstfit")>avgscore.get("worstfit")){
                           System.out.println(list[0].name +" and "+ list[1].name + " have the same efficieny while " + list[2].name+" has lesser efficieny");
                           
                       }
                       else if(avgscore.get("bestfit")==avgscore.get("worstfit")&&avgscore.get("worstfit")>avgscore.get("firstfit")){
                           System.out.println(list[1].name +" and "+ list[2].name + " have the same efficieny while " + list[0].name+" has lesser efficieny");
                           
                       }
                      else if(avgscore.get("worstfit")==avgscore.get("bestfit")&&avgscore.get("bestfit")>avgscore.get("firstfit")){
                           System.out.println(list[1].name +" and "+ list[2].name + " have the same efficieny while " + list[0].name+" has lesser efficieny");
                           
                       }
                       else if(avgscore.get("worstfit")==avgscore.get("firstfit")&&avgscore.get("firstfit")>avgscore.get("bestfit")){
                           System.out.println(list[0].name +" and "+ list[2].name + " have the same efficieny while " + list[1].name+" has lesser efficieny");
                           
                       }
                       else System.out.println("They've the same efficieny");
                      
              
   
    

              
 

}
}
}


    

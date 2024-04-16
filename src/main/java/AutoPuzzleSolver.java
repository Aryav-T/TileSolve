import java.util.ArrayList;

public class AutoPuzzleSolver {
   public static void main(String[] args) {
        int[][] var = {{1,2,3}, {0,5,6}, {4,7,8}};
       State intial = new State(var);
       int[][] goal = {{1,2,3}, {4,5,6}, {7,8,0}};

       State finalOne = solveD(intial, goal);
       ArrayList<State> solutions = path(intial, finalOne);
       System.out.println("Intial: ");
       System.out.println(intial);
       for (int i = 0; i < solutions.size(); i++) {
           System.out.println(i+1+" step: ");
           System.out.println( solutions.get(i));
       }
   }

   public static State solveB(State intial, int[][] goal){
       ArrayList<State> seen = new ArrayList<>();
       ArrayList<State> toVisit = new ArrayList<>();
       toVisit.add(intial);
       while (toVisit.size()>= 0){
           State current = toVisit.remove(toVisit.size()-1);
           if (current.equals(goal)) return current;

               ArrayList<State> nextStates = current.getNextStates();
               seen.add(current);
               for (int i = 0; i < nextStates.size(); i++) {
                   if (!seen.contains(nextStates.get(i))) {
                       toVisit.add(nextStates.get(i));
                       seen.add(nextStates.get(i));
                   }
               }

       }
       return intial;
   }
   public static State solveD(State intial, int[][] goal){
       ArrayList<State> seen = new ArrayList<>();
       ArrayList<State> toVisit = new ArrayList<>();
       toVisit.add(intial);
       while (toVisit.size()>= 0){
           State current = toVisit.remove(toVisit.size()-1);
           if (current.equals(goal)) return current;
           if(current.getDepth()< 12) {
               ArrayList<State> nextStates = current.getNextStates();
               seen.add(current);
               for (int i = 0; i < nextStates.size(); i++) {
                   if (!seen.contains(nextStates.get(i))) {
                       nextStates.get(i).setDepth(current.getDepth()+1);
                       toVisit.add(nextStates.get(i));
                       seen.add(nextStates.get(i));
                   }
               }
           }
       }
       return intial;
   }
   public static ArrayList<State> path(State intial, State solution){
       ArrayList<State> solutions = new ArrayList<>();
       solutions.add(solution);
       while(!(solution.getParent()).equals(intial)){

           solutions.add(0, solution.getParent());
           solution = solution.getParent();
       }
       return solutions;
   }
}

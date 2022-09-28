import computation.contextfreegrammar.*;
import computation.parser.*;
import computation.parsetree.*;
import computation.derivation.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Parser implements IParser {
  private Derivation acceptedderivvation;
  public boolean isInLanguage(ContextFreeGrammar cfg, Word w){
    // checks for only terminal input.
    for (Symbol S:w){
      if (!S.isTerminal()){
      return false;
      }
    }

    ArrayList<Derivation> derivationsearchlist = getallDerivations(cfg, w.length());
      for (Derivation currentderiv: derivationsearchlist){
        Word latestword = currentderiv.getLatestWord();
        if (latestword.equals(w)){
          acceptedderivvation = currentderiv;
          return true;
        }
      }
    return false;
  } 

  /*
Method iterates through all of the derivations currents available, and takes the latest word formulated. It then 
iterates through all of the symbols in the word and checks if any rule can be applied to it. If a rule can be applied, it applies it
and adds the step to the derivation, then updating the total derivations list. This will iterate through all possible rules that can be applied
and return all possible derivations that are available, including if they do not yield in valid words, ie the word is 01A0.
*/
  private ArrayList<Derivation> getallDerivations(ContextFreeGrammar cfg, int n){
    // start variable in list
    ArrayList<Derivation> nstepderivationlist = new ArrayList<Derivation>();
    nstepderivationlist.add(new Derivation(new Word(cfg.getStartVariable())));
    if (n == 0){
      n=1;
    }
    int steps = 0;
    
    while (steps < 2*n-1){
      // dummy list
      ArrayList<Derivation> currentlyactivederivations = new ArrayList<Derivation>();
      for(Derivation currentderivation: nstepderivationlist){
        Word latestword = currentderivation.getLatestWord();
        int indexer = 0;
        for (Symbol s : latestword){
         
          for(Rule currentrule : cfg.getRules()){
            // if rule can be implemented, then add a step to the derivation
            if (s.equals(currentrule.getVariable())){
              // updates the word with the new rule
              Word newWord = latestword.replace(indexer, currentrule.getExpansion());
              Derivation newDerivation = new Derivation(currentderivation);
              newDerivation.addStep(newWord, currentrule, indexer);
              currentlyactivederivations.add(newDerivation);
            }
          }
          indexer = indexer + 1;
        }
      }
      // saves list
      nstepderivationlist = currentlyactivederivations;
      steps = steps+1;
    }
//    returnallderivations(nstepderivationlist);
    return nstepderivationlist;
  } 


// test print function, only works properly for assigned cfg
  private void returnallderivations(ArrayList<Derivation> printlist){

    for (Derivation x: printlist){
      Word latestword = x.getLatestWord();
      String latestwordstring = latestword.toString();
    if(!latestwordstring.matches(".*[STFXLRPM].*"))
    {
      System.out.println(latestword.toString());
    }
     
    
    }

  
  }
/*
Algorithm iterates through rules backwards, identifies an expansion terminal node, creates the node and attaches a parent node to it. These parent nodes are then stored in a stack
If the expansion is not of length 1, then as it is in CNF, it must be 2 parent nodes. So it takes the last 2 nodes from the stack and creates a parent node for them, then adds that to the stack
If the Variable of the rule is the start variable, then it terminates and prints the top node of the parent stack
*/
  public ParseTreeNode generateParseTree(ContextFreeGrammar cfg, Word w) {
    if (isInLanguage(cfg, w)){
      ArrayList<ParseTreeNode> terminallist = new ArrayList<ParseTreeNode>();
      ArrayList<ParseTreeNode> parents = new ArrayList<ParseTreeNode>();
      ParseTreeNode parentnode1;
      if (w.equals(Word.emptyWord)){
        ParseTreeNode emptytree = ParseTreeNode.emptyParseTree(cfg.getStartVariable());
        return emptytree;
      }
      for(Step x: acceptedderivvation){
//        System.out.println(x.getRule());
        Rule currentrule = x.getRule();
        Variable ruleleft = currentrule.getVariable();
        Word ruleright = currentrule.getExpansion();
        if (ruleright.length() == 1){
          // terminal check is redundant because CNF means that length 1 will give 
          // creates a  terminal node equal to the expansion of the rule
          for (Symbol s:ruleright){
            ParseTreeNode childnode1 = new ParseTreeNode(s);
          terminallist.add(childnode1);
          // adds a parent node to the terminal
          parentnode1 = new ParseTreeNode(ruleleft,childnode1);
          parents.add(parentnode1);
          if (ruleleft == cfg.getStartVariable()){
            return parents.get(parents.size()-1);
          }
        }
        
        }
        if (ruleright.length() == 2){
          parentnode1 = new ParseTreeNode(ruleleft, parents.get(parents.size()-1), parents.get(parents.size()-2));
          // removes the latest 2 nodes
          parents.remove(parents.size()-1);
          parents.remove(parents.size()-1);
          parents.add(parentnode1);
          if (ruleleft == cfg.getStartVariable()){
            return parents.get(parents.size()-1);
          }
        }
        }
      }
      return null;
    }

}
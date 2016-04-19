import java.util.Scanner;    // Specific to Java 1.5.x

public class ExpressionTree
{
    double x=0;
    static class TreeNode
   {
      boolean  leaf;   // ?Is this a leaf? else internal
      char     op;     // For an internal node, the operator
      double   value;  // For a leaf, the value
      TreeNode left,   // Left subexpression for an internal node
                             right;  // Right subexpression

      // Bare-bones constructor
      private TreeNode ( boolean leaf, char op, double value )
      {
         this.leaf    = leaf;
         this.op      = op;
         this.value   = value;
         this.left    = null;   // Empty to start
         this.right   = null;
      }

      // For leaf nodes, show the value; for internal, the operator.
      public String toString()    // To override Object.toString, must be public.
      {  return leaf ? Double.toString(value) : Character.toString(op) ;  }
   }

   TreeNode root = null;

   public ExpressionTree ( Scanner input )
   {  root = build(input);  }

  private TreeNode build ( Scanner input )
   {
      boolean  leaf;
      String   token;
      double   value;
      TreeNode node;

      leaf = input.hasNextDouble();
      if ( leaf )
      {
         value = input.nextDouble();
         node = new TreeNode ( leaf, '\0', value );
      }
      else
      {
         token = input.next();
         node  = new TreeNode ( leaf, token.charAt(0), 0.0 );
         node.left  = build ( input );
         node.right = build ( input );
      }
      return node;
   }

  public void resetT1(ExpressionTree a, int i){
      
  }
  public ExpressionTree crossover(ExpressionTree a, ExpressionTree b) {
        ExpressionTree node = a;
        int angkaRandom = 1 + (int) (Math.random() * 2);
        if (angkaRandom == 1) {
            node.root.right = b.root.left;
        } else if (angkaRandom == 2) {
            node.root.left = b.root.right;
        }

        return node;
    }

    public ExpressionTree mutasi(ExpressionTree a) {
        char[] operator = {'+', '-', '*', '/'};
        char operatorTerpilih = operator[(0 + (int) (Math.random() * 3))];
        int angkaIf = 0 + (int) (Math.random() * 2);
        if (angkaIf == 1) {
            a.root.right.op = operatorTerpilih;
        } else if (angkaIf == 2) {
            a.root.left.op = operatorTerpilih;
        } else {
            operatorTerpilih = operator[(0 + (int) (Math.random() * 3))];
            a.root.left.op = operatorTerpilih;
            operatorTerpilih = operator[(0 + (int) (Math.random() * 3))];
            a.root.right.op = operatorTerpilih;
        }
        return a;

    }
    public String tampilString(TreeNode node) {  // NOTE:  removing tail recursion
        String hasil = "";
        hasil += node + " ";
        hasil += node.left + " ";
        hasil += node.left.left + " ";
        hasil += node.left.right + " ";
        hasil += node.right + " ";
        hasil += node.right.left + " ";
        hasil += node.right.left;
        return hasil;
    }

    public String tampilString2(TreeNode node) {  // NOTE:  removing tail recursion
        String hasil = "(";
        if (node.left.left.value==21.0) {
            hasil += "x";
        } else {
            hasil += node.left.left + " ";
        }
        hasil += node.left + " ";
        if (node.left.right.value==21.0) {
            hasil += "x";
        } else {
            hasil += node.left.right + " ";
        }
        hasil += ") " + node + " (";
        if (node.right.left.value==21.0) {
            hasil += "x";
        } else {
            hasil += node.right.left + " ";
        }
        hasil += node.right + " ";
        if (node.right.right.value==21.0) {
            hasil += "x";
        } else {
            hasil += node.right.right + " ";
        }
        hasil += ")";
        return hasil;
    }
   public void showPostFix ()
   {
      showPostFix ( root );
      System.out.println();
   }

   // Postfix expression is the result of a post-order traversal
   private void showPostFix ( TreeNode node )
   {
      if ( node != null )
      {
         showPostFix ( node.left );
         showPostFix ( node.right );
         System.out.print ( node + " " );
      }
   }

/**
 * Show the expression tree as a prefix expression.
 * All the work is done in the private recursive method.
 */
   public void showPreFix ()
   {
      showPreFix ( root );
      System.out.println();
   }

   // Prefix expression is the result of a pre-order traversal
   private void showPreFix ( TreeNode node )
   {  // NOTE:  removing tail recursion
      while ( node != null )
      {
         System.out.print ( node + " " );
         showPreFix ( node.left );
         node = node.right;  // Update parameter for right traversal
      }
   }

/**
 * Show the expression tree as a parenthesized infix expression.
 * All the work is done in the private recursive method.
 */
   public void showInFix ()
   {
      showInFix ( root );
      System.out.println();
   }

   // Parenthesized infix requires parentheses in both the
   // pre-order and post-order positions, plus the node
   // itself in the in-order position.
   private void showInFix ( TreeNode node )
   {
      if ( node != null )
      {
         // Note:  do NOT parenthesize leaf nodes
         if ( ! node.leaf )
            System.out.print ("( ");        // Pre-order position
         showInFix ( node.left );
         System.out.print ( node + " " );   // In-order position
         showInFix ( node.right );
         if ( ! node.leaf )                 // Post-order position
            System.out.print (") ");
      }
   }

  public double evaluate ()
   {  return root == null ? 0.0 : evaluate ( root ) ;  }

   private double evaluate ( TreeNode node )
   {
      double result;    // Value to be returned

      if ( node.leaf )        // Just get the value of the leaf
         result = node.value;
      else
      {
         // We've got work to do, evaluating the expression
         double left, right;
         char   operator = node.op;

         // Capture the values of the left and right subexpressions
         left  = evaluate ( node.left );
         right = evaluate ( node.right );

         // Do the arithmetic, based on the operator
         switch ( operator )
         {
            case '-':  
                if(left==21)
                    result = x - right;
                else if(right==21)
                    result = left - x;
                else
                    result  = left - right;
                break;
            case '*':  
                if(left==21)
                    result = x * right;
                else if(right==21)
                    result = left * x;
                else
                    result  = left * right;
                break;
            case '/':  
                if(left==21)
                    result = x / right;
                else if(right==21)
                    result = left / x;
                else
                    result  = left / right;
                break;
            case '^':  
                if(left==21)
                    result = Math.pow (x, right );  
                else if(right==21)
                    result = Math.pow (left, x );  
                else
                    result  = Math.pow (left, right );  
                break;
                
         // NOTE:  allow fall-through from default to case '+'
            default:   System.out.println ("Unrecognized operator " +
                          operator + " treated as +.");
            case '+':  
                if(left==21)
                    result = x + right;
                else if(right==21)
                    result = left + x;
                else
                    result  = left + right;
                break;
         }
      }
      // Return either the leaf's value or the one we just calculated.
      return result;
   }
}

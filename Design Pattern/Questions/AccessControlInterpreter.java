// AccessControlInterpreter.java

import java.util.*;

// Step 1: Expression Interface
interface Expression {
    boolean interpret(Set<String> userRoles);
}

// Step 2: Terminal Expression for Roles
class RoleExpression implements Expression {
    private String role;

    public RoleExpression(String role) {
        this.role = role;
    }

    public boolean interpret(Set<String> userRoles) {
        return userRoles.contains(role);
    }
}

// Step 3: Non-Terminal Expressions

// AND
class AndExpression implements Expression {
    private Expression expr1, expr2;

    public AndExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public boolean interpret(Set<String> userRoles) {
        return expr1.interpret(userRoles) && expr2.interpret(userRoles);
    }
}

// OR
class OrExpression implements Expression {
    private Expression expr1, expr2;

    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public boolean interpret(Set<String> userRoles) {
        return expr1.interpret(userRoles) || expr2.interpret(userRoles);
    }
}

// NOT
class NotExpression implements Expression {
    private Expression expr;

    public NotExpression(Expression expr) {
        this.expr = expr;
    }

    public boolean interpret(Set<String> userRoles) {
        return !expr.interpret(userRoles);
    }
}

// Step 4: Client/Test
public class AccessControlInterpreter {
    public static void main(String[] args) {
        // User roles
        Set<String> user1Roles = new HashSet<>(Arrays.asList("Editor"));
        Set<String> user2Roles = new HashSet<>(Arrays.asList("Admin", "Guest"));
        Set<String> user3Roles = new HashSet<>(Arrays.asList("Editor", "User"));

        // Expression: Admin OR (Editor AND NOT Guest)
        Expression admin = new RoleExpression("Admin");
        Expression editor = new RoleExpression("Editor");
        Expression guest = new RoleExpression("Guest");

        Expression editorAndNotGuest = new AndExpression(editor, new NotExpression(guest));
        Expression accessRule = new OrExpression(admin, editorAndNotGuest);

        // Evaluate access
        System.out.println("User 1 has access? " + accessRule.interpret(user1Roles)); // true
        System.out.println("User 2 has access? " + accessRule.interpret(user2Roles)); // true (Admin)
        System.out.println("User 3 has access? " + accessRule.interpret(user3Roles)); // true
    }
}

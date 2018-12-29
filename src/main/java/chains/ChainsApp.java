package chains;

import java.io.IOException;

/**
 * Programmatic access to chains.cc workspace.
 */
public class ChainsApp
{
    public static void main(String[] args)
    {
        try
        {
            ChainsEngine chainsEngine = new ChainsEngine();
            chainsEngine.process("user", "pass"); // Update here to run
        }
        catch (IOException e)
        {
            System.out.println("Problem Encountered");
        }
    }
}

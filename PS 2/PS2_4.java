import mpi.MPI;
import java.util.Arrays;

public class PS2_4 {
    public static void main(String[] args) {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        final int unitSize = 5;
        final int totalElements = unitSize * size;
        final int root = 0;
        
        int[] sendBuffer = new int[totalElements];
        int[] receiveBuffer = new int[unitSize];

        if (rank == root) {
            for (int i = 0; i < totalElements; i++) {
                sendBuffer[i] = i + 1;
            }
            System.out.println("Root Array = " + Arrays.toString(sendBuffer));
        }

        MPI.COMM_WORLD.Scatter(sendBuffer, 0, unitSize, MPI.INT, receiveBuffer, 0, unitSize, MPI.INT, root);

        double[] localReciprocal = new double[unitSize];
        for (int i = 0; i < unitSize; i++) {
            localReciprocal[i] = Math.round(1.0 / receiveBuffer[i] * 100.0) / 100.0;
        }

        for (int i = 0; i < size; i++) {
            MPI.COMM_WORLD.Barrier();
            if (rank == i) {
                System.out.println("Process " + rank
                    + " chunk = " + Arrays.toString(receiveBuffer)
                    + " reciprocal = " + Arrays.toString(localReciprocal)
                );
            }
        }

        double[] finalReciprocal = new double[totalElements];

        MPI.COMM_WORLD.Gather(localReciprocal, 0, unitSize, MPI.DOUBLE, finalReciprocal, 0, unitSize, MPI.DOUBLE, root);

        if (rank == root) System.out.println("Final Reciprocal Array = " + Arrays.toString(finalReciprocal));

        MPI.Finalize();
    }
}

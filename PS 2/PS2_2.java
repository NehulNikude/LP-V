import mpi.MPI;
import java.util.Arrays;

public class PS2_2 {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        final int unitSize = 3;
        final int totalElements = unitSize * size;
        final int root = 0;
        
        int[] sendBuffer = new int[totalElements];
        int[] receiveBuffer = new int[unitSize];

        if (rank == root) {
            for (int i = 0; i < totalElements; i++) {
                sendBuffer[i] = i+1;
            }
            System.out.println("Root Array = " + Arrays.toString(sendBuffer));
        }

        MPI.COMM_WORLD.Scatter(sendBuffer, 0, unitSize, MPI.INT, receiveBuffer, 0, unitSize, MPI.INT, root);

        int localProd = 1;
        for (int x: receiveBuffer) localProd *= x;

        for (int i = 0; i < size; i++) {
            MPI.COMM_WORLD.Barrier();
            if (rank == i) {
                System.out.println("Process " + rank
                    + " chunk = " + Arrays.toString(receiveBuffer)
                    + " local product = " + localProd
                );
            }
        }

        int[] reduceSend = {localProd};
        int[] finalProd = new int[1];

        MPI.COMM_WORLD.Reduce(reduceSend, 0, finalProd, 0, 1, MPI.INT, MPI.PROD, root);

        if (rank == root) System.out.println("Final Product = " + finalProd[0]);

        MPI.Finalize();
    }
}

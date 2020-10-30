
package algoritmohuffman;
abstract class ArvoreHuffman implements Comparable<ArvoreHuffman>{
    public final int frequencia;

    public ArvoreHuffman(int frequencia) {
        this.frequencia = frequencia;
    }
    
    @Override
    public int compareTo(ArvoreHuffman avr){
        return frequencia - avr.frequencia;
    }
}

package algoritmohuffman;
public class NoHuffman extends ArvoreHuffman {
    public final ArvoreHuffman esquerda, direita;

    public NoHuffman(ArvoreHuffman esquerda, ArvoreHuffman direita) {
        super(esquerda.frequencia + direita.frequencia);
        this.esquerda = esquerda;
        this.direita = direita;
    }
    
    
}

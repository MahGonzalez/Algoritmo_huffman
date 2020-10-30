package algoritmohuffman;

import java.util.PriorityQueue;

public class CodigoHuffman {

    public static void main(String[] args) {
        String teste = "A aranha arranha a rã";
        int[] freqChar = new int[256];
        for (char c : teste.toCharArray()) {
            freqChar[c]++;
        }
        ArvoreHuffman avr = constroiArvore(freqChar);
        System.out.println("TABELA DE CÓDIGOS");
        System.out.println("SÍMBOLO\t QUANTIDADE\t HUFFMAN CÓDIGO");
        imprimeCodigos(avr, new StringBuffer());

        //compacta
        String codifica = codifica(avr, teste);
        System.out.println("\nTEXTO COMPACTADO");
        System.out.println(codifica);

        //decodifica
        System.out.println("\nTEXTO DECODIFICADO");
        System.out.println(decodifica(avr, codifica));
    }

    public static ArvoreHuffman constroiArvore(int[] freqChar) {
        PriorityQueue<ArvoreHuffman> avrs = new PriorityQueue<>();
        for (int i = 0; i < freqChar.length; i++) {
            if (freqChar[i] > 0) {
                avrs.offer(new FolhaHuffman(freqChar[i], (char) i));
            }
        }
        while (avrs.size() > 1) {
            ArvoreHuffman a = avrs.poll();
            ArvoreHuffman b = avrs.poll();
            avrs.offer(new NoHuffman(a, b));
        }
        return avrs.poll();
    }

    public static String codifica(ArvoreHuffman avr, String codifica) {
        assert avr !=null;
        String textoCodificado = "";
        for(char c: codifica.toCharArray()){
            textoCodificado += (pegaCodigo(avr, new StringBuffer(), c));
        }
        return textoCodificado;
    }
    
    public static String decodifica (ArvoreHuffman avr, String codifica){
        assert avr != null;
        String textoDecodificado = "";
        NoHuffman no = (NoHuffman)avr;
        for(char codigo: codifica.toCharArray()){
            if(codigo == '0'){
                if(no.esquerda instanceof FolhaHuffman){
                    textoDecodificado += ((FolhaHuffman)no.esquerda).valor;
                    no = (NoHuffman)avr;
                }else{
                    no = (NoHuffman)no.esquerda;
                }
            }else if(codigo =='1'){
                if(no.direita instanceof FolhaHuffman){
                    textoDecodificado +=((FolhaHuffman)no.direita).valor;
                    no = (NoHuffman)avr;
                }else{
                    no = (NoHuffman)no.direita;
                }
            }
        }
        return textoDecodificado;
    }
    public static void imprimeCodigos(ArvoreHuffman avr, StringBuffer prefixo){
        assert avr != null;
        
        if(avr instanceof FolhaHuffman){
            FolhaHuffman folha = (FolhaHuffman)avr;
            
            System.out.println(folha.valor + "\t" + folha.frequencia + "\t\t" + prefixo);
            
        }else if(avr instanceof NoHuffman){
            NoHuffman no = (NoHuffman)avr;
            
            prefixo.append('0');
            imprimeCodigos(no.esquerda, prefixo);
            prefixo.deleteCharAt(prefixo.length()-1);
            
            prefixo.append('1');
            imprimeCodigos(no.direita, prefixo);
            prefixo.deleteCharAt(prefixo.length()-1);
        }
    }
    public static String pegaCodigo(ArvoreHuffman avr, StringBuffer prefixo, char w){
        assert avr != null;
        if(avr instanceof FolhaHuffman){
            FolhaHuffman folha = (FolhaHuffman)avr;
            if(folha.valor == w){
                return prefixo.toString();
            }
        }else if(avr instanceof NoHuffman){
            NoHuffman no = (NoHuffman)avr;
            prefixo.append('0');
            String esquerda = pegaCodigo(no.esquerda, prefixo,w);
            prefixo.deleteCharAt(prefixo.length()-1);
            
            prefixo.append('1');
            String direita = pegaCodigo(no.direita, prefixo, w);
            prefixo.deleteCharAt(prefixo.length()-1);
            
            if(esquerda == null)return direita; else return esquerda;
        }
        return null;
    }
}

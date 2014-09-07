package br.com.popeye.controle;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.JOptionPane;

import bemajava.Bematech;

public class ImpressaoNaoFiscal {
	private static PrintService impressora;  
    
    public static List<String> retornaImressoras(){  
        try {  
            List<String> listaImpressoras = new ArrayList<>();  
            DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;    
            PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);    
            for (PrintService p : ps) {    
                listaImpressoras.add(p.getName());       
            }    
            return listaImpressoras;  
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
        return null;  
    }  
      
    public void detectaImpressoras(String impressoraSelecionada) {    
        try {    
            DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;    
            PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);    
            for (PrintService p : ps) {    
                if(p.getName()!=null && p.getName().contains(impressoraSelecionada)){    
                    impressora = p;    
                }       
            }    
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
    }  
      
    public  boolean imprime(String texto) throws FileNotFoundException {    
    	detectaImpressoras("MP-4200 TH");
    	
        if (impressora == null) {    
            JOptionPane.showMessageDialog(null, "Nennhuma impressora foi encontrada. Instale uma impressora padrão \r\n(Generic Text Only) e reinicie o programa.");   
        } else {    
            try {    
                DocPrintJob dpj = impressora.createPrintJob();    
                InputStream stream = new ByteArrayInputStream((texto).getBytes());    
                DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;    
                Doc doc = new SimpleDoc(stream, flavor, null);    
                dpj.print(doc, null);
                
                return true;    
            } catch (PrintException e) {    
                e.printStackTrace();    
            }    
        }    
        return false;    
    }   
  
    public void acionarGuilhotina() throws FileNotFoundException{
    	int iRetorno = Bematech.AcionaGaveta();
    	switch (iRetorno){
	    	case 0: {
	    		JOptionPane.showMessageDialog(null, "Erro de comunicação");
	    		break;
	    	}
	    	case 1: {
	    		JOptionPane.showMessageDialog(null, "OK");
	    		break;
	    	}
	    	case -4:{
	    		JOptionPane.showMessageDialog(null, "O arquivo de inicialização BemaFI32.ini não foi encontrado no diretório de sistema do Windows.");
	    		break;
	    	}
	    	case -5:{
	    		JOptionPane.showMessageDialog(null, "Erro ao abrir porta de comunicação!");
	    	}
	    	case -27:{
	    		JOptionPane.showMessageDialog(null, "Status da impressora difere de 6,0,0 (ACK, ST1 e ST2)!");
	    		break;
	    	}
	    	default:{
	    		JOptionPane.showMessageDialog(null, "Verificar!");
	    	}	
    	}
        imprime(""+(char)27+(char)109);
        imprime(""+(char)27+(char)118+(char)140);
    }  
}

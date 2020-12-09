public class ImplExample implements Hello{
	public String printMsg(String pregunta){
		 String preguntas []= {
            "COMO TE LLAMAS","DONDE VIVES","EN QUE ESCUELA ESTUDIAS", "EN QUE SEMESTRE VAS", "ERES REGULAR", "TIENES NOVIA", "CUAL ES TU PROMEDIO", "CHIVAS O AMERICA", "CUANDO ES TU CUMPLEAÑOS"
    };
        String respuestas []= {"MI NOMBRE ES TAMAGOCHI","EN LA COMPU","EN ESCOM","2DO SEMESTRE", "SI, SOY REGULAR", "NO, estoy solo :C", "9.9 COMO DEBE DE SER", "CHIVAS", "EL 31 DE AGOSTO"};
        for(int i = 0 ; i < 10; i++)
            if(preguntas[i].equals(pregunta))
                return respuestas[i];
		return "";
	}
}

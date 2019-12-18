package net.geforcemods.rologia.os.speech;

//import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
//import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
//import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;

// TODO
public class SpeechRecognizer {
		
	/*public static void recognizeSpeech(File audio, String... keywords) {
		SpeechToText speechToText = new SpeechToText();
		speechToText.setUsernameAndPassword("42b9ad70-c378-430c-ba0f-46f7e489ccfb", "KOecVXjgzdSM");

		try {
		    RecognizeOptions.Builder options = new RecognizeOptions.Builder()
		      .audio(audio)
		      .contentType("audio/wav")
		      .timestamps(true)
		      .wordAlternativesThreshold((float) 0.9);
		    
		    if(keywords.length != 0)
		    {
		    	options = options.keywords(Arrays.asList(keywords)).keywordsThreshold((float) 0.9);
		    }
		    
		    RecognizeOptions recogOptions = options.build();

		    SpeechRecognitionResults speechRecognitionResults = speechToText.recognize(recogOptions).execute();
		    System.out.println(speechRecognitionResults);
		} catch (FileNotFoundException e) {
		  e.printStackTrace();
		}
	}*/

}
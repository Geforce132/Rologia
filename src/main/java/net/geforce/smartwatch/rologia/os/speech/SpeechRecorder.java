package net.geforce.smartwatch.rologia.os.speech;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class SpeechRecorder {
	
	private static final String FILE_NAME = "temp";
	private static final String FILE_SUFFIX = ".wav";
	
	private static Thread recordingThread;
	private static TargetDataLine dataLine;
	private static boolean isRecording;
	
	public static void startRecordingMicrophoneInput() {
		AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);

		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		
		if(!AudioSystem.isLineSupported(info))
			System.out.println("error");
		
		try {
			dataLine = (TargetDataLine)AudioSystem.getLine(info);
			dataLine.open(format);
		}
		catch(LineUnavailableException e) {
			e.printStackTrace();
		}
		
		dataLine.start();
		
		recordingThread = new Thread(){
			@Override
			public void run()
			{
				AudioInputStream inputStream = new AudioInputStream(dataLine);
				final File audio = new File(FILE_NAME + FILE_SUFFIX);
				
				try {
					AudioSystem.write(inputStream, AudioFileFormat.Type.WAVE, audio);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		recordingThread.start();
		isRecording = true;
	}
	
	public static void stopRecordingMicrophoneInput() {
		dataLine.stop();
		dataLine.close();
		
		recordingThread = null;
		dataLine = null;
		
		isRecording = false;
	}

	public static File getLatestRecording() {
		return new File(FILE_NAME + FILE_SUFFIX);
	}
	
	public static void deleteRecording() {
		File file = new File(FILE_NAME + FILE_SUFFIX);
		file.delete();
	}
	
	public static boolean isRecording() {
		return isRecording;
	}
}
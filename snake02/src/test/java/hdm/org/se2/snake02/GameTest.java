package hdm.org.se2.snake02;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import junit.framework.Assert;

public class GameTest {

	@Test
	public void testSceneException () {
	
		try {
			Window.sceneHandler(Window.unknown);
			fail("Exception");
		} catch (Exception e) {
			assertTrue(e instanceof NullPointerException);		
		}
	}
	
	@Test
	public void testSettingsDifficulty(){
	
		Settings s = new Settings();
		s.setDifficulty("Medium");
		Assert.assertEquals(s.getDifficulty(), "Medium");
		}
	
	@Test
	public void testMode(){
		Settings se = new Settings();
		se.setMode("Standard");
		Assert.assertEquals(se.getMode(), "Standard");
	}
	
	@Test
	public void negativeModeTest(){
		Settings neSe = new Settings();
		try{
			neSe.setMode("Standi");
		}catch (Exception e) {
				assertTrue(e instanceof IOException);
			}
		}
	



}



	
	


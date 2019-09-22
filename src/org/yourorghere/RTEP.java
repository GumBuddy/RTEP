package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import static javax.media.opengl.GL.GL_MODELVIEW;
import static javax.media.opengl.GL.GL_PROJECTION;
import static javax.media.opengl.GL.GL_QUADS;
import static javax.media.opengl.GL.GL_TRIANGLES;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class RTEP implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new RTEP());
        frame.add(canvas);
        frame.setSize(500, 500);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        
        gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
        gl.glClearDepth(1.0);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        
        gl.glLoadIdentity();
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        
        
        /*Perspectiva 2 
        glu.gluPerspective(30,1.0,1.0,100.0);
        Perspectiva 3 
        glu.gluPerspective(60.0,0.5,1.0,100.0);
        Perspectiva 4 
        glu.gluPerspective(60.0,2.0,1.0,100.0);
        Perspectiva 5 
        glu.gluPerspective(60.0,1.0,1.0,100.0);
        gl.glOrtho(-1.0,1.0,-1.0,1.0,-1.0,1.0);
        
        gl.glTranslatef(0, 0, -2);
        gl.glMatrixMode(GL_MODELVIEW);
        */
        
        
        
        gl.glBegin(GL_QUADS);
        gl.glColor3f(1.0f,1.0f,1.0f);
        gl.glVertex3f(-0.5f,0.5f,-0.5f);
        gl.glColor3f(0.0f,0.0f,1.0f);
        gl.glVertex3f(-0.5f,-0.5f,0.5f);
        gl.glColor3f(0.0f,1.0f,0.0f);
        gl.glVertex3f(0.5f,-0.5f,0.5f);
        gl.glColor3f(1.0f,0.0f,0.0f);
        gl.glVertex3f(0.5f,0.5f,-0.5f);
       
        gl.glEnd();
        
      
        
        /*
        gl.glOrtho(-1.0,1.0,-1.0,1.0,-1.0,1.0);
        
        gl.glScalef(2.0f, 0.5f, 1.0f);
        gl.glRotatef(45, 0, 0, 1);
        gl.glTranslatef(0.0f,0.2f,0.5f);

        gl.glBegin(GL_TRIANGLES);
        gl.glColor3f(0.5f,0.8f,0.5f);
        gl.glVertex3f(0.0f,0.3f,0.0f);
        gl.glColor3f(0.0f,1.0f,0.5f);
        gl.glVertex3f(-0.7f,-0.5f,0.0f);
        gl.glColor3f(0.5f,0.0f,1.0f);
        gl.glVertex3f(0.3f,-0.2f,0.0f);
        gl.glEnd();
        
        */
       
        gl.glFlush();
        
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}


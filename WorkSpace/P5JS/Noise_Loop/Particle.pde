class Particle {
  NoiseLoop xNoise;
  NoiseLoop yNoise;
  NoiseLoop dNoise;
  NoiseLoop sNoise; //Scale noise
  NoiseLoop rNoise;
  NoiseLoop gNoise;
  NoiseLoop bNoise;
  NoiseLoop alphaNoise;
  
  Particle() {
    xNoise = new NoiseLoop(.2, -width, width);
    yNoise = new NoiseLoop(.2, -height, height);
    dNoise = new NoiseLoop(.2, 10, 90);
    sNoise = new NoiseLoop(.2, .9, 1.11);
    rNoise = new NoiseLoop(.9, 0, 255);
    gNoise = new NoiseLoop(.6, 0, 50);
    bNoise = new NoiseLoop(.2, 0, 255);
    alphaNoise = new NoiseLoop(.2, 20, 255);
  }
  
  void render(float a) {
    //stroke(255);
    noStroke();
    float r = rNoise.value(a);
    float g = gNoise.value(a);
    float b = bNoise.value(a);
    float alpha = alphaNoise.value(a);
    fill(r, g, b, alpha);
    float x = xNoise.value(a);
    float y = yNoise.value(a);
    float d = dNoise.value(a);
    float s = sNoise.value(a);
    scale(s);
    circle(x, y, d);
  }
  
}

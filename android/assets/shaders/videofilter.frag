#ifdef GL_ES
precision mediump float;
precision mediump int;
#else
#define highp;
#endif

#define freqStart -1.0
#define freqInterval 0.1
#define sampleSize 0.02           // How accurately to sample spectrum, must be a factor of 1.0

uniform vec3 resolution3;           // viewport resolution (in pixels)
uniform sampler2D u_texture;          // input channel. XX = 2D/Cube
varying vec4 v_color;
varying vec2 v_texCoord;

void main() {
    vec2 xy = v_texCoord.xy / resolution3.xy;
    // first texture row is frequency data
    // sample intensities in frequency interval.
    float intensity = 0.0;
    for(float s = 0.0; s < freqInterval; s += freqInterval * sampleSize) {
        intensity += texture2D(u_texture, vec2(freqStart + s, 0.0)).r;
    }
    intensity = abs(intensity);
    intensity = pow((intensity*sampleSize),3.0)*4.0;
    //set offsets
    vec2 rOffset = vec2(-0.02,0)*intensity;
    vec2 gOffset = vec2(0.0,0)*intensity;
    vec2 bOffset = vec2(0.04,0)*intensity;
    vec4 rValue = texture2D(u_texture, xy - rOffset);
    vec4 gValue = texture2D(u_texture, xy - gOffset);
    vec4 bValue = texture2D(u_texture, xy - bOffset);
    gl_FragColor = vec4(rValue.r, gValue.g, bValue.b, 1.0);
}

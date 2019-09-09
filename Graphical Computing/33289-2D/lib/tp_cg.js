function contorno(c2d)
{  
	c2d.beginPath();
	c2d.moveTo(-0.410, -1.00);
	c2d.lineTo( 0.410, -1.00);
	c2d.quadraticCurveTo( 0.40,-0.85, 0.75,-0.85); 
	c2d.lineTo( 1.00, -0.65);
	c2d.quadraticCurveTo( 0.94,-0.65, 0.94, 0.00);
	c2d.arcTo( 0.80, 0.65, 0.00, 0.990, 0.92);
	c2d.lineTo(0.00, 0.990);
	c2d.arcTo(-0.80, 0.65,-0.94,0.00, 0.92);
	c2d.lineTo(-0.94, 00);
	c2d.quadraticCurveTo(-0.94,-0.65,-1.00,-0.65);
	c2d.lineTo(-0.75, -0.85);
	c2d.quadraticCurveTo(-0.40,-0.85,-0.41,-1.00);
	c2d.lineTo(-0.41, -1.00);
	c2d.closePath();
}

function cruz(c2d)
{
	c2d.beginPath();
		c2d.moveTo( 0.038, -0.03);
		c2d.lineTo( 0.038, -0.28); 
		c2d.lineTo(-0.038, -0.28);
		c2d.lineTo(-0.038, -0.03);
		c2d.lineTo(-0.34,  -0.03); 
		c2d.lineTo(-0.34,   0.03);
		c2d.lineTo(-0.038,  0.03);
		c2d.lineTo(-0.038,  0.28); 
		c2d.lineTo( 0.038,  0.28);
		c2d.lineTo( 0.038,  0.03);
		c2d.lineTo( 0.34,   0.03); 
		c2d.lineTo( 0.34,  -0.03);
		c2d.lineTo( 0.038, -0.03);
	c2d.closePath();
}

function referencial(c2d)
{
	c2d.beginPath();
		c2d.moveTo( -1.00,  0.00);
		c2d.lineTo(  1.00,  0.00);
		c2d.moveTo(	 0.00, -1.00);
		c2d.lineTo(	 0.00,  1.00);
	c2d.closePath();
}

function pilar_cima_esquerda(c2d)
{
	c2d.beginPath();
		c2d.moveTo( -0.78, -0.78);
		c2d.lineTo( -0.18, -0.29);
		c2d.lineTo( -0.18, -0.14);
		c2d.lineTo( -0.35, -0.14);
		c2d.lineTo( -0.97, -0.67);
		c2d.lineTo( -0.78, -0.78);
	c2d.closePath();
}

function pilar_cima_direita(c2d)
{
	c2d.beginPath();
		c2d.moveTo(  0.78, -0.78);
		c2d.lineTo(  0.18, -0.29);
		c2d.lineTo(  0.18, -0.14);
		c2d.lineTo(  0.35, -0.14);
		c2d.lineTo(  0.97, -0.67);
		c2d.lineTo(  0.78, -0.78);
	c2d.closePath();
}


function pilar_baixo_esquerda(c2d)
{
	c2d.beginPath();
		c2d.moveTo( -0.75, 0.50);
		c2d.lineTo( -0.35, 0.14);
		c2d.lineTo( -0.18, 0.14);
		c2d.lineTo( -0.18, 0.29);
		c2d.lineTo( -0.61, 0.67);
		c2d.lineTo( -0.75, 0.50);
	c2d.closePath();
}

function pilar_baixo_direita(c2d)
{
	c2d.beginPath();
		c2d.moveTo( 0.75, 0.50);
		c2d.lineTo( 0.35, 0.14);
		c2d.lineTo( 0.18, 0.14);
		c2d.lineTo( 0.18, 0.29);
		c2d.lineTo( 0.61, 0.67);
		c2d.lineTo( 0.75, 0.50);
	c2d.closePath();
}

function letter_f(c2d)
{
	c2d.beginPath();
		c2d.moveTo(-0.08,  -0.8);
		c2d.lineTo( 0.10,  -0.8);
		c2d.lineTo( 0.10,  -0.77);
		c2d.lineTo(-0.027, -0.77);
		c2d.lineTo(-0.027, -0.70);
		c2d.lineTo( 0.08,  -0.7);
		c2d.lineTo( 0.08,  -0.675);
		c2d.lineTo(-0.03,  -0.675);
		c2d.lineTo(-0.03,  -0.58 );
		c2d.lineTo(-0.08,  -0.58);
		c2d.lineTo(-0.08,  -0.8);
	c2d.closePath();
}

function letter_b(c2d)
{
	c2d.beginPath();
		c2d.lineTo(0.8, 0.036);
		c2d.lineTo(0.652, 0.036);
		c2d.lineTo(0.652, -0.09);
		c2d.arc(0.8,-0.03,0.065,4.71,1.57);
	c2d.closePath();
}

function letter_c(c2d)
{
	c2d.beginPath();
		c2d.arc(-1.1, 0.04,0.15,0.6, 5.7);
	c2d.closePath();
}

function trapezio(c2d)
{
	c2d.beginPath();
		c2d.moveTo(-1.2, -0.04);
		c2d.lineTo(-1.0, -0.06);
		c2d.lineTo(-1.0,  0.1);
		c2d.lineTo(-1.2,  0.08);
	c2d.closePath();
}


function into(c, x, y, sx, sy, ang) 
{
	c.save();
	c.translate(x,y);
	c.rotate(ang)
	c.scale(sx,sy);
}

function style(c, fill, stroke, thick) 
{
	c.fillStyle = fill;
	c.strokeStyle = stroke;
	c.fill();
	c.stroke();
	c.lineWidth = thick;
}

function leave(c) 
{
	c.restore();
}

function quadrado(c2d) 
{
	c2d.fillStyle = "red";
	c2d.fillRect(-1,-1,2,2);
}

function trabalho(c2d, pos, rot)
{
	into(c2d,0,0,1,1,0);
		contorno(c2d);
	leave(c2d);
	style(c2d, "#03197f", "#03197f", 0.01);

	into(c2d,0,0,0.96,0.95,0);
		contorno(c2d);
	leave(c2d);
	style(c2d, "white", "#03197f", 0.01);

	into(c2d, 0,0.02, 0.55, 0.45,0);
		quadrado(c2d);
	leave(c2d);

	into(c2d, 0,0.005, 1, 1,0 + rot.alpha);
		cruz(c2d);
	leave(c2d);
	style(c2d, "white", "white", 0.01);

	into(c2d,-300 + pos.x ,-300+pos.x ,1,1,0);
		pilar_cima_esquerda(c2d);
	leave(c2d);
	style(c2d, "#03197f", "#03197f", 0.01);

	into(c2d,300 - pos.x ,-300 + pos.x ,1,1,0);
		pilar_cima_direita(c2d);
	leave(c2d);
	style(c2d, "#03197f", "#03197f", 0.01);

	into(c2d,-300 + pos.x ,300 - pos.x,1,1,0);
		pilar_baixo_esquerda(c2d);
	leave(c2d);
	style(c2d, "#03197f", "#03197f", 0.01);

	into(c2d,300 - pos.x ,300 - pos.x,1,1,0);
		pilar_baixo_direita(c2d);
	leave(c2d);
	style(c2d, "#03197f", "#03197f", 0.01);

	into(c2d,0,0,0.98,0.98,0);
		letter_f(c2d);
	leave(c2d);
	style(c2d, "yellow", "yellow", 0.01);

	into(c2d,0,0,0.98,0.98,0);
		letter_b(c2d);
	leave(c2d);
	style(c2d, "yellow", "yellow", 0.01);

	into(c2d,0,-0.13,0.98,0.98,0);
		letter_b(c2d);
	leave(c2d);
	style(c2d, "yellow", "yellow", 0.01);

	into(c2d,0.37,-0.015,0.5,0.5,0);
		letter_b(c2d);
	leave(c2d);
	style(c2d, "white", "white", 0.01);

	into(c2d,0.37,-0.14,0.5,0.5,0);
		letter_b(c2d);
	leave(c2d);
	style(c2d, "white", "white", 0.01);

	into(c2d,0.37,-0.14,0.98,0.98,0);
		letter_c(c2d);
	leave(c2d);
	style(c2d, "yellow", "yellow", 0.01);

	into(c2d,0.037,-0.13,0.68,0.68,0);
		letter_c(c2d);
	leave(c2d);
	style(c2d, "white", "white", 0.01);

	into(c2d,-0.18,-0.12,0.38,0.98,0);
		trapezio(c2d);
	leave(c2d);
	style(c2d, "white", "white", 0.01);	
}

scene = function(c2d, rot, pos) {
    
    this.c2d = c2d;
    this.rot = rot;
    this.pos = pos;

    var grad = c2d.createLinearGradient(10,25,20,10);
    
    grad.addColorStop(0.3, "black");
    grad.addColorStop(0.7, "Blue");
    this.drawFrame = function() {
        
        this.c2d.fillStyle = grad;
        this.c2d.fillRect(0,0,600,600);

        into(c2d, 300, 300, 300, 300,0);
			c2d.lineWidth = 1.0 / 300.0;
			trabalho(c2d, pos, rot); 
		leave(c2d);
    }
    
    this.animate = function() 
{
        
        this.drawFrame();

        requestAnimationFrame(this.animate);

        TWEEN.update();        
    }

    return this;
}


function main() {

    var c2d = document.getElementById("canvas").getContext("2d");     

    var rot = {alpha: 0};
    var target_rot= {alpha: 6.28};
    var rot_t= new TWEEN.Tween(rot).to(target_rot, 9000);
    rot_t.easing(TWEEN.Easing.Elastic.InOut);
    rot_t.start();

    var pos = {x: 0, y: 0, z: 0};
    var pos_target= {x: 300, y: 167.5, z: 135}
    var pos_t= new TWEEN.Tween(pos).to(pos_target, 7000);
    pos_t.easing(TWEEN.Easing.Elastic.InOut);
    pos_t.start();

    var s = scene(c2d,rot,pos);
 
    s.animate();
}

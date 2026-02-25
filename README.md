<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>🎯 Number Guessing Game — Java Swing UI</title>
</head>
<body>

<h1>🎯 Number Guessing Game — Java Swing UI</h1>

<h2>🧠 Overview</h2>
<p>
This project is an <strong>interactive Number Guessing Game</strong> built using <strong>Java Swing</strong>.
It combines:
</p>

<ul>
<li>🔐 User login &amp; password authentication</li>
<li>🎮 Interactive guessing gameplay</li>
<li>📊 Multi-user score tracking</li>
<li>🔄 Restart and Exit controls</li>
<li>💾 Persistent storage using text files</li>
</ul>

<p>
The application uses a <strong>CardLayout-based GUI</strong> to switch between the login screen and the game screen.
</p>

<hr/>

<h2>🖥️ User Interface Flow</h2>

<h3>🔐 Login Screen</h3>
<ul>
<li>User enters <strong>username and password</strong></li>
<li>If user exists → password is verified</li>
<li>If user is new → account is automatically registered</li>
<li>On success → user is taken to the game screen</li>
</ul>

<p><strong>File used:</strong> users.txt</p>

<hr/>

<h3>🎮 Game Screen</h3>
<ul>
<li>Number input field</li>
<li>Guess button</li>
<li>Status message label</li>
<li>Best/Worst stats label</li>
<li>Restart button</li>
<li>Exit button</li>
</ul>

<hr/>

<h2>⚙️ How the Game Works</h2>

<h3>1️⃣ Game Initialization</h3>
<ul>
<li>A random number (1–100) is generated</li>
<li>Try counter resets to 0</li>
<li>UI switches to game panel</li>
</ul>

<pre><code>randomNumber = new Random().nextInt(100) + 1;
tries = 0;</code></pre>

<hr/>

<h3>2️⃣ Guess Handling</h3>
<ul>
<li>Input is parsed to integer</li>
<li>Try count increases</li>
<li>Program compares guess with random number</li>
</ul>

<p><strong>Possible outcomes:</strong></p>
<ul>
<li>🎉 Correct → score saved</li>
<li>📉 Too low → hint shown</li>
<li>📈 Too high → hint shown</li>
<li>⚠ Invalid input → error message</li>
</ul>

<hr/>

<h3>3️⃣ Score Tracking (Multi-User)</h3>

<pre><code>scores.txt</code></pre>

<p>Format:</p>

<pre><code>username,tries</code></pre>

<ul>
<li>🏆 Best (minimum tries)</li>
<li>😅 Worst (maximum tries)</li>
</ul>

<hr/>

<h3>4️⃣ Restart Feature</h3>
<ul>
<li>New random number generated</li>
<li>Try counter reset</li>
<li>Input field cleared</li>
<li>Game continues without logging out</li>
</ul>

<hr/>

<h3>5️⃣ Exit Feature</h3>

<pre><code>System.exit(0);</code></pre>

<p>The application closes immediately.</p>

<hr/>

<h2>🔐 Authentication Logic</h2>

<p><strong>Method:</strong> authenticate(user, pass)</p>

<ul>
<li>If user exists → password checked</li>
<li>If password correct → login success</li>
<li>If user not found → auto-register</li>
</ul>

<p>Credentials stored in:</p>

<pre><code>users.txt</code></pre>

<p>Format:</p>

<pre><code>username,password</code></pre>

<hr/>

<h2>📁 Project Files</h2>

<pre><code>Game.java
users.txt      (auto-created)
scores.txt     (auto-created)
README.md</code></pre>

<hr/>

<h2>▶️ How to Run</h2>

<h3>Compile</h3>
<pre><code>javac Game.java</code></pre>

<h3>Execute</h3>
<pre><code>java Game</code></pre>

<hr/>

<h2>👨‍💻 Author</h2>
<p>
<strong>Darshan</strong><br/>
Information Science Engineering Student
</p>

</body>
</html>

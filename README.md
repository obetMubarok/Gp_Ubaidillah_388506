# Gp_Ubaidillah_388506<br>
NAMA 	: UBAIDILLAH HUSNI MUBAROK<br/>
NIM	: 388506<br/>
Judul 	: "Aplikasi Genetic Programming dalam Pencarian Rumus Deret Bilangan"<br/>
<br/>
<br/>
Genetic Programming<br/>
-------------------
1. Inisialisasi (tree)<br/>
	terdiri dari 2 Binary tree yang masing-masing terdiri dari 3 operator dan 4 operand. <br/>
	daftar operator(+, -, /, *) dan daftar operand(1,2,3,4,5,6,7,8,9,x)<br/>
<br/>
2. Evaluasi<br/>
	dengan cara menghitung nilai fitness yg tertinggi dari kedua tree tersebut, yg terbaik di masukkan ke tabel hasil<br/>
<br/>
3. Crossover<br/>
	dengan cara menukar salah satu node random dari tree1 dengan node tree1<br/>
	<br/>
4. Mutasi<br/>
	dengan cara mengubah operator secara random pada node tree<br/>
5. seleksi<br/>
	memeilih 2 node yang terbaik<br/>
<br/>
<br/>
keterangan Program : <br/>
program awal berjalan akan mengeksekusi rumus deret (x + x * x) dan menghasilkan deret 2 6 12 20 30 42 56<br/>
kemudian program akan mencari rumus dengan menggunakan GP, proses iterasi dilakukan sebanyak 100 kali dan nilai fitness yang mendekati angka 1 adalah rumus yang terbaik dari 100 yang ditemukan.

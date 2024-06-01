import java.util.Scanner;

public class AksiAdmin extends Aksi {
    @Override
    public void tampilanAksi() {
        System.out.println("Aksi Admin:");
        System.out.println("1. Tambah Film");
        System.out.println("2. Lihat List Film");
        System.out.println("3. Logout");
        System.out.println("4. Tutup Aplikasi");
    }

    @Override
    public void keluar() {
        Akun.logout();
        System.out.println("Anda telah logout.");
    }

    @Override
    public void tutupAplikasi() {
        System.out.println("Aplikasi ditutup.");
        System.exit(0);
    }

    @Override
    public void lihatListFilm() {
        // Implementasi melihat list film
        // Untuk mengambil semua film dari daftar film dan mencetak informasi film satu per satu
        Film.getFilms().values().forEach(film -> 
            System.out.println(film.getName() + " - " + film.getDescription() + " - Harga: " + film.getPrice() + " - Stok: " + film.getStock())
        );
    }

    public void tambahFilm() {
        // Implementasi menambahkan film
        Scanner scanner = new Scanner(System.in);
        // Untuk meminta pengguna untuk memasukkan informasi film baru
        System.out.print("Nama Film: ");
        String name = scanner.nextLine();
        System.out.print("Deskripsi Film: ");
        String description = scanner.nextLine();
        System.out.print("Harga Tiket: ");
        double price = scanner.nextDouble();
        System.out.print("Stok Tiket: ");
        int stock = scanner.nextInt();

        Film.addFilm(name, description, price, stock);
        scanner.close();
 }
}

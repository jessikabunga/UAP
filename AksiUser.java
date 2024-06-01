import java.util.Map;
import java.util.Scanner;

public class AksiUser extends Aksi {
    @Override
    public void tampilanAksi() {
        System.out.println("Aksi User:");
        System.out.println("1. Pesan Film");
        System.out.println("2. Lihat Saldo");
        System.out.println("3. Lihat List Film");
        System.out.println("4. Lihat Pesanan");
        System.out.println("5. Logout");
        System.out.println("6. Tutup Aplikasi");
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
        Film.getFilms().values().forEach(film -> 
            System.out.println(film.getName() + " - " + film.getDescription() + " - Harga: " + film.getPrice() + " - Stok: " + film.getStock())
        );
    }

    public void lihatSaldo() {
        // Implementasi lihat Saldo
        System.out.println("Saldo anda: " + Akun.getCurrentUser().getSaldo());
    }

    public void pesanFilm() {
        // Implementasi pemesanan film
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nama Film yang ingin dipesan: ");
        String name = scanner.nextLine();
        // Untuk mendapatkan objek Film dari daftar film berdasarkan nama yang dimasukkan pengguna
        Film film = Film.getFilms().get(name);

        // Untuk memeriksa apakah film yang diminta tersedia
        if (film == null) {
            System.out.println("Film yang dicari tidak ditemukan.");
            return;
        }

        System.out.print("Jumlah tiket yang ingin dipesan: ");
        int kuantitas = scanner.nextInt();
        double totalHarga = kuantitas * film.getPrice();

        // Untuk memeriksa ketersediaan stok tiket
        if (kuantitas > film.getStock()) {
            System.out.println("Stok tiket tidak mencukupi.");
        } else if (totalHarga > Akun.getCurrentUser().getSaldo()) {
            // Untuk memeriksa apakah saldo pengguna mencukupi untuk memesan tiket
            System.out.println("Saldo tidak mencukupi, saldo yang dimiliki " + Akun.getCurrentUser().getSaldo());
        } else {
            // Jika tiket dapat dipesan, maka mengurangi stok film dan mengurangi saldo pengguna
            film.setStock(film.getStock() - kuantitas);
            Akun.getCurrentUser().setSaldo(Akun.getCurrentUser().getSaldo() - totalHarga);
            Akun.getCurrentUser().addPesanan(film, kuantitas);
            System.out.println("Tiket berhasil dipesan.");
        }
    }

    public void lihatPesanan() {
        // Implementasi melihat pesanan
        Map<String, Pesanan> pesanan = Akun.getCurrentUser().getPesanan();
        if (pesanan.isEmpty()) {
            System.out.println("Kamu belum pernah melakukan pemesanan.");
        } else {
            pesanan.values().forEach(p -> 
                System.out.println("Film: " + p.getFilm().getName() + " - Jumlah: " + p.getKuantitas() + " - Total Harga: " + (p.getKuantitas() * p.getFilm().getPrice()))
            );
    }
    }
}
package com.projetoreprodutor.projetoreprodutor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import javafx.animation.TranslateTransition;
import javafx.animation.Animation;
import javafx.util.Duration;
import javafx.scene.Node;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelloController {

    @FXML
    private MediaView mediaView;

    @FXML
    private AnchorPane telaApp;

    @FXML
    private Slider tempoMusica;

    @FXML
    private Slider SeletorVolume;

    @FXML
    private Label tempoAtual;

    @FXML
    private Label tempoTotal;

    @FXML
    private Label nomeMusica;
    private MediaPlayer mediaPlayer;
    private List<String> musicas;
    private int indiceMusicaAtual;

    public void initialize() {
        carregarMusica();
        formatarNomeMusica();
        movimentarNomeMusica();
    }

    private void movimentarNomeMusica() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(30), nomeMusica);

        transition.setFromX(350);
        transition.setToX(-670);

        transition.setCycleCount(Animation.INDEFINITE);
        transition.play();
    }




    private void carregarMusica() {
        musicas = new ArrayList<>();
        for (String s : Arrays.asList("C:/Users/leyvi/IdeaProjects/ProjetoReprodutor/ProjetoReprodutor/music/Declan DP - Journey.mp3", "C:/Users/leyvi/IdeaProjects/ProjetoReprodutor/ProjetoReprodutor/musicSilent-Partner-Payday.mp3"))
            musicas.add(s);
        indiceMusicaAtual = 0;
        tocarMusicaAtual();
    }

    private void tocarMusicaAtual() {
        File musicaAtual = new File(musicas.get(indiceMusicaAtual));
        if (!musicaAtual.exists()) {
            System.out.println("Arquivo não encontrado: " + musicaAtual.getAbsolutePath());
            return;
        }

        Media media = new Media(musicaAtual.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
    }

    private void formatarNomeMusica(){
        String nome = new File(musicas.get(indiceMusicaAtual)).getName();
        nomeMusica.setText(nome);
    }

    public void fechar() {
        System.exit(0);
    }

    public void minimizar()
    {
        Stage stage = (Stage) telaApp.getScene().getWindow(); stage.setIconified(true);
    }
    @FXML
    private void player() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        } else {
            System.out.println("mediaPlayer está nulo. Certifique-se de que carregarMusica foi chamado corretamente.");
        }
    }

    public void anterior() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        indiceMusicaAtual--;
        if (indiceMusicaAtual < 0) {
            indiceMusicaAtual = musicas.size() - 1;
        }
        tocarMusicaAtual();
        formatarNomeMusica();
    }

    public void proximo() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        indiceMusicaAtual++;
        if (indiceMusicaAtual >= musicas.size()) {
            indiceMusicaAtual = 0;
        }
        tocarMusicaAtual();
        formatarNomeMusica();
    }

    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void pare() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void mudo() {
        if (mediaPlayer != null) {
            mediaPlayer.setMute(!mediaPlayer.isMute());
        }
    }
}

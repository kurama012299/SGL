package main

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.layout.StackPane

object MyApp extends JFXApp {
  val button = new Button("Click Me")
  button.onAction = _ => println("¡Botón clickeado!")

  val root = new StackPane {
    children = Seq(button)
  }

  stage = new JFXApp.PrimaryStage {
    title = "Mi Aplicación ScalaFX"
    scene = new Scene(root, 300, 200)
  }
}
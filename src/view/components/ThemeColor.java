package view.components;

import java.awt.Color;

/**
 * Tokens de color corporativos oficiales del Sistema Finandes.
 * Estricta paridad con AGENTS.md y los diseños en Recursos/*.jpg.
 */
public final class ThemeColor {

    private ThemeColor() {}

    // Paleta Institucional Finandes (Extraído con exactitud de Recursos/*.jpg)
    public static final Color VERDE_PRIMARIO = new Color(66, 173, 77);      // #42AD4D
    public static final Color VERDE_ACENTO   = new Color(74, 186, 86);      // #4ABA56
    public static final Color VERDE_HOVER    = new Color(52, 150, 62);      // #34963E
    public static final Color VERDE_SEPARADOR = new Color(85, 192, 96);     // #55C060
    public static final Color VERDE_TEXTO_MUTED = new Color(197, 230, 201); // #C5E6C9

    // Acciones y Botones
    public static final Color CIAN_ACCION    = new Color(0, 159, 227);      // #009FE3
    public static final Color CIAN_HOVER     = new Color(2, 132, 199);      // #0284C7
    public static final Color CIAN_LIGHT     = new Color(224, 242, 254);    // #E0F2FE

    // Fondos y Contenedores
    public static final Color FONDO_GLOBAL   = new Color(248, 250, 252);    // #F8FAFC
    public static final Color FONDO_CARD     = new Color(255, 255, 255);    // #FFFFFF
    public static final Color BORDE_CARD     = new Color(226, 232, 240);    // #E2E8F0
    public static final Color BORDE_INPUT    = new Color(203, 213, 225);    // #CBD5E1

    // Tipografía
    public static final Color TEXTO_TITULO   = new Color(15, 23, 42);       // #0F172A
    public static final Color TEXTO_SUBTITULO = new Color(30, 41, 59);      // #1E293B
    public static final Color TEXTO_SECUNDARIO = new Color(100, 116, 139);  // #64748B
    public static final Color TEXTO_MUTED    = new Color(148, 163, 184);    // #94A3B8

    // Tablas
    public static final Color TABLA_HEADER_BG = new Color(248, 250, 252);   // #F8FAFC
    public static final Color TABLA_HEADER_TEXT = new Color(71, 85, 105);   // #475569
    public static final Color TABLA_ROW_DIVIDER = new Color(241, 245, 249); // #F1F5F9
    public static final Color TABLA_ROW_SELECTED = new Color(240, 253, 244);// #F0FDF4

    // Semáforos y Badges (Fondos y Textos)
    public static final Color BADGE_SUCCESS_BG   = new Color(209, 250, 229); // #D1FAE5
    public static final Color BADGE_SUCCESS_TEXT = new Color(16, 185, 129);  // #10B981

    public static final Color BADGE_WARNING_BG   = new Color(254, 243, 199); // #FEF3C7
    public static final Color BADGE_WARNING_TEXT = new Color(217, 119, 6);   // #D97706

    public static final Color BADGE_DANGER_BG    = new Color(254, 226, 226); // #FEE2E2
    public static final Color BADGE_DANGER_TEXT  = new Color(239, 68, 68);   // #EF4444

    public static final Color BADGE_TEAL_BG      = new Color(230, 255, 250); // #E6FFFA
    public static final Color BADGE_TEAL_TEXT    = new Color(13, 148, 136);  // #0D9488

    public static final Color BADGE_INFO_BG      = new Color(224, 242, 254); // #E0F2FE
    public static final Color BADGE_INFO_TEXT    = new Color(3, 105, 161);   // #0369A1

    public static final Color BADGE_NEUTRAL_BG   = new Color(241, 245, 249); // #F1F5F9
    public static final Color BADGE_NEUTRAL_TEXT = new Color(71, 85, 105);   // #475569
}

package com.jakode.eventa.data

import android.net.Uri
import com.jakode.eventa.model.Event

object DataFake {
    fun getData(): List<Event> = listOf(
        Event(
            title = "CONCERT",
            description = "The Weekend",
            moreDetails = "Lorem ipsum dolor sit amet, consectetur elit adipiscing elit. Venenatis pulvinar a amet in, suspendisse vitae, posuere eu tortor et. Und commodo, fermentum, mauris leo eget.",
            starting = "STARTING 9:10 PM",
            month = "DEC",
            day = 21,
            price = "\$19.99/person",
            Uri.parse("https://s3-alpha-sig.figma.com/img/3645/8df2/991effbe0974e0cc205747e6e7694550?Expires=1610928000&Signature=hv8wtrFYqNzxeY2QG9iqjDxRzqqysRB6UPz-DVRM1ogxkj~lY7TVAT761Lbqu2xtmpKH8sZlsSE6CVtc-ati3Hc9wCKicuROG1SYAHM3tkIck0TRjgwYqoLPpIVy7OOSQPSbO-3aGAvlOZpyRbw~G8wfKEpxwPur9ihdIu7j8BQ5yqMSRBLR8KWMvARYC2E5JdRCpdhU1bo8r-l4n9xnCsti6C6Fbx-XrIYHwL0RzLu1nIFZEUm5nADWHRH2Jl0nH9lcbD1cPUPHc9inoUU94sSVq-flajzC-dH2kLEFjc7FC50BflDFoS2xOor2EUJjXHimDVb5KIoRbg8x3yEdpg__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"),
            Uri.parse("https://s3-alpha-sig.figma.com/img/3101/7d34/4d053351042b48f1db418a01676ab77e?Expires=1610928000&Signature=CHn9MLIauo7uB1gKfbf3U8ZJSUad3Ze0hmAjLDaINztNuWSn3hHzGWQ50n2MhBPEHg-0uVkntaap0a5ttx8VNxhr4o3gyUOLbVaertTone1ZBXLoGNCitaIcHHKsfwgLpbTXqrBDks4PXCWSBA58gmrPAb9JjB-sl8kkAGb4UvIZaCfmulGeHmVwPuxv01jIuRxHb~oQNvjoGTLuYOkeOqDaxAzF-T8Uq2gwpGa7C9vc9Kb1WhdX8EZUG49zGit8SggdiymfkZZNT63oE1r~AGOuzxRoSFimCfbCM4w3wDXogyEflYE6Ke13WKnBCoRKPWl-Yeve5wfkM3RDCnDrFg__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA")
        ),
        Event(
            title = "SHOW",
            description = "Firemasters",
            moreDetails = "Lorem ipsum dolor sit amet, consectetur elit adipiscing elit. Venenatis pulvinar a amet in, suspendisse vitae, posuere eu tortor et. Und commodo, fermentum, mauris leo eget.",
            starting = "STARTING 10:10 PM",
            month = "DEC",
            day = 21,
            price = "\$17.60/person",
            Uri.parse("https://s3-alpha-sig.figma.com/img/9f42/db0b/d1f083bd1a1860a6823a1a34d16107ef?Expires=1610928000&Signature=cZOjUfyKWCls4IABC9fp8ueScRCeEc0YWl0hUxgT0SvPbUX3WApFu~mHPtEo6yeJyo0yIxvQ-8gpEZtbllYOXhjLquEYIPkgCgvpp63J53OYkMihFtvv6ZVgCJ5qSQl7OlZFwXQ9XIDcA61VGveO2dtykQ-FXuImolypLWat0QVkMLZF9KGKV2foF2JDIiYc9oFmxtq6wiwjENij46zAxXWwYbLGgkirbLLVas3vfUDhIsvFq9N993nOcNckkuPotu3VDP8DrHP1Qu7T5avDrHUZaq1zifYmI01Ylu504jg~i6ILqA8rdOo9zlVAzdrHmEeyiTwJ65tYv677qTd9CQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"),
            Uri.parse("https://s3-alpha-sig.figma.com/img/3101/7d34/4d053351042b48f1db418a01676ab77e?Expires=1610928000&Signature=CHn9MLIauo7uB1gKfbf3U8ZJSUad3Ze0hmAjLDaINztNuWSn3hHzGWQ50n2MhBPEHg-0uVkntaap0a5ttx8VNxhr4o3gyUOLbVaertTone1ZBXLoGNCitaIcHHKsfwgLpbTXqrBDks4PXCWSBA58gmrPAb9JjB-sl8kkAGb4UvIZaCfmulGeHmVwPuxv01jIuRxHb~oQNvjoGTLuYOkeOqDaxAzF-T8Uq2gwpGa7C9vc9Kb1WhdX8EZUG49zGit8SggdiymfkZZNT63oE1r~AGOuzxRoSFimCfbCM4w3wDXogyEflYE6Ke13WKnBCoRKPWl-Yeve5wfkM3RDCnDrFg__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA")
        )
    )
}
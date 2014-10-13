XPDays2014: JUnit HierarchicalContextRunner: Mehr Struktur in Unit-Test bringen!
================================================================================

Jeder, der sich mit Ruby Specs auskennt, weiß, dass eine hierarchische Struktur innerhalb der Unit Tests hilft die Tests deutlich sauberer und lesbarer zu machen. Außerdem wird dadurch die Anzahl von Boilerplate Setup Code entschieden reduziert.

Ich persönlich war sehr enttäuscht, dass JUnit keine derartige Unterstützung für hierarchische Tests bietet. Workarounds, die mithilfe von inneren statischen Klassen und Vererbung Abhilfe schaffen, “verschlimmbessern” das Grundproblem meines Erachtens nur: Denn der Test-Code wird noch weniger lesbar!

Als Erweiterung zu JUnit bietet der HierarchicalContextRunner eine wirklich Lösung für dieses Problem. Mithilfe von inneren Klassen können Kontexte (Gruppierungen) erzeugt werden, die zu verschiedenen Zwecken genutzt werden können. Dieser Vortrag zeigt die Stärken des HierarchicalContextRunners für JUnit auf und gibt eine Idee, wie Testfälle deutlich sauberer strukturiert werden können.

Weitere Infos: https://github.com/bechte/junit-hierarchicalcontextrunner/wiki

Dieses Repository beinhaltet alle Vortragsfolien und Materialien.

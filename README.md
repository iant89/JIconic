# JIconic
A Super Easy Java Icon Library.

## Why JIconic?
Do you need to use a icon font in your java application, and want customization? Look no-more! JIconic is super easy to use. New fonts are super easy to add support for, and each icon is customizable with primary and secondary colors with more features planned to be added.

## Supported Font Providers
- Font Awesome PRO v5

## Planned Providers
- Font Awesome FREE
- Material Design
- Open Iconic

## Examples
Fonts are fetched using a prefix along with the icon name.

Font Awesome prefixes:
- fal (Light)
- far (Regular)
- fas (Solid)
- fad (Duotone)
- fab (Brand)

Material Design Prefixes:
 - mdo (Outlined)
 - md (Regular)
 - mdr (Round)
 - mds (Sharp)
 - mdt (Two Tone)

Registering a Font Provider:
```java
JIconic.registerProvider(new FontAwesomeLightProvider());
```
> You only have to register providers once at startup, and you can register as many as you need in your application.

JIconic can create Icons or Images of the requested icon.

Font Awesome Basic Icon Creation:
```java
JIconic.registerProvider(new FontAwesomeLightProvider());

ImageIcon icon = (ImageIcon) JIconic.buildIcon("fal-cctv", new IconProperties().setPrimaryColor(Color.BLACK).setSize(100));
```

Font Awesome Duotone Icon Creation:
```java
JIconic.registerProvider(new FontAwesomeDuotoneProvider());

ImageIcon icon = (ImageIcon) JIconic.buildIcon("fad-cctv", new IconProperties().setPrimaryColor(Color.BLACK).setSecondaryColor(Color.DARK_GRAY).setSize(100));
```
Font Awesome Basic Icon Image Creation:
```java
JIconic.registerProvider(new FontAwesomeDuotoneProvider());

Image image = JIconic.buildImage("fad-cctv", new IconProperties().setPrimaryColor(Color.BLACK).setSecondaryColor(Color.DARK_GRAY).setSize(100));
```

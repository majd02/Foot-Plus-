<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Cocur\Slugify\Slugify;

/**
 * Stade
 *
 * @ORM\Table(name="stade")
 * @ORM\Entity(repositoryClass="App\Repository\StadeRepository")
 * @UniqueEntity("nom")
 */
class Stade
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_stade", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idStade;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=10, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="adresse", type="string", length=20, nullable=false)
     */
    private $adresse;

    /**
     * @var int
     *
     * @Assert\Range(min=10,max=1000)
     * @ORM\Column(name="prixph", type="integer", nullable=false)
     */
    private $prixph;

    /**
     * @var string
     * @ORM\Column(name="contact", type="string", length=10, nullable=false)
     */
    private $contact;

    /**
     * @var string
     * @Assert\File(mimeTypes={"image/jpeg"})
     * @ORM\Column(name="image", type="string", length=250, nullable=false)
     */
    private $image;

    public function getIdStade(): ?int
    {
        return $this->idStade;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }
    public function getSlug():string
    {
        return (new Slugify())->slugify($this->nom);

    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getPrixph(): ?int
    {
        return $this->prixph;
    }

    public function setPrixph(int $prixph): self
    {
        $this->prixph = $prixph;

        return $this;
    }

    public function getContact(): ?string
    {
        return $this->contact;
    }

    public function setContact(string $contact): self
    {
        $this->contact = $contact;

        return $this;
    }

    public function getImage()
    {
        return $this->image;
    }

    public function setImage($image)
    {
        $this->image = $image;

        return $this;
    }
    public function __toString():String
    {
        return (string) $this->nom;
        return (string) $this->idStade;
    }

}

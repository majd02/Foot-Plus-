<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Stade
 *
 * @ORM\Table(name="stade")
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\StadeRepository")
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
     * @ORM\Column(name="prixph", type="integer", nullable=false)
     */
    private $prixph;

    /**
     * @var string
     *
     * @ORM\Column(name="contact", type="string", length=10, nullable=false)
     */
    private $contact;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=10, nullable=false)
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

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(string $image): self
    {
        $this->image = $image;

        return $this;
    }


}

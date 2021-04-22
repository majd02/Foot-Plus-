<?php

namespace App\Entity;


use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints\DateTime;

use Symfony\Component\Validator\Constraints as Assert;


/**
 * Feedback
 *
 * @ORM\Table(name="feedback", indexes={@ORM\Index(name="IDX_D2294458A2D72265", columns={"idU"})})

 *@ORM\Entity(repositoryClass="App\Repository\FeedbackRepository")
 */
class Feedback
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255, nullable=false)
     */
    private $description;

    /**
     * @var int
     *
     * @ORM\Column(name="rate", type="integer", nullable=false)
     *@Assert\Range(min=1,max=5)
     */
    private $rate;

    /**
     * @var \DateTime
     * @ORM\Column(name="date", type="datetime", nullable=false, options={"default"="current_timestamp()"})

     */
    private $date ;

    /**
     * @var string|null
     *
     * @ORM\Column(name="idU", type="string", length=255, nullable=true, options={"default"="NULL"})
     */
    private $idu = 'NULL';

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId(int $id): void
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getDescription(): ?string
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription(string $description): void
    {
        $this->description = $description;
    }

    /**
     * @return int
     */
    public function getRate(): ?int
    {
        return $this->rate;
    }

    /**
     * @param int $rate
     */
    public function setRate(int $rate): void
    {
        $this->rate = $rate;
    }

    /**
     * @return \DateTime
     */
    public function getDate(): \DateTime
    {
            return new \DateTime('now');

    }

    /**
     * @param \DateTime $date
     */
    public function setDate(\DateTime $date ): void
    {
        $this->date = $date;
    }

    /**
     * @return string|null
     */
    public function getIdu(): ?string
    {
        return $this->idu;
    }

    /**
     * @param string|null $idu
     */
    public function setIdu(?string $idu): void
    {
        $this->idu = $idu;
    }



}
